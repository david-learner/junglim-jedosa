package com.jedosa.junglim.article;

import com.jedosa.junglim.account.AccountRepository;
import com.jedosa.junglim.account.domain.Account;
import com.jedosa.junglim.account.domain.SessionAccountDto;
import com.jedosa.junglim.article.domain.Article;
import com.jedosa.junglim.article.domain.Comment;
import com.jedosa.junglim.article.domain.Pagination;
import com.jedosa.junglim.article.dto.ArticleDto;
import com.jedosa.junglim.article.dto.ArticlesDto;
import com.jedosa.junglim.article.dto.CommentDto;
import com.jedosa.junglim.article.repository.ArticleRepository;
import com.jedosa.junglim.article.repository.ArticleSearchCondition;
import com.jedosa.junglim.article.repository.CommentRepository;
import com.jedosa.junglim.exception.NoAccountException;
import com.jedosa.junglim.exception.NoArticleException;
import com.jedosa.junglim.exception.NoCommentException;
import com.jedosa.junglim.exception.NotOwnException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class CommentService {

    private static final Logger log = LoggerFactory.getLogger(CommentService.class);
    private final CommentRepository commentRepository;
    private final AccountRepository accountRepository;
    private final ArticleRepository articleRepository;

    public CommentService(CommentRepository commentRepository, AccountRepository accountRepository, ArticleRepository articleRepository) {
        this.commentRepository = commentRepository;
        this.accountRepository = accountRepository;
        this.articleRepository = articleRepository;
    }

    @Transactional
    public CommentDto saveComment(Long articleId, Long accountId, CommentDto commentDto) {
        Article article = articleRepository.findById(articleId).orElseThrow(NoArticleException::new);
        Account account = accountRepository.findById(accountId).orElseThrow(NoAccountException::new);
        Comment createdComment = commentRepository.save(new Comment(article, account, commentDto.getContent()));
        return new CommentDto(createdComment);
    }

    @Transactional
    public CommentDto replyToQuestion(Long articleId, Long accountId, CommentDto commentDto) {
        Article article = articleRepository.findById(articleId).orElseThrow(NoArticleException::new);
        article.replied();
        articleRepository.save(article);
        Account account = accountRepository.findById(accountId).orElseThrow(NoAccountException::new);
        Comment createdComment = commentRepository.save(new Comment(article, account, commentDto.getContent()));
        return new CommentDto(createdComment);
    }

    public List<CommentDto> getCommentsOfArticle(Long articleId) {
        List<Comment> comments =
                commentRepository.findAllByArticle_IdAndDeletedOrderByCreatedDateTimeAsc(articleId, false);
        return comments.stream().map(CommentDto::new).collect(Collectors.toList());
    }

    @Transactional
    public void updateComment(Long commentId, CommentDto commentDto, SessionAccountDto sessionAccountDto) {
        Account account = accountRepository.findById(sessionAccountDto.getId()).orElseThrow(NoAccountException::new);
        Comment comment = commentRepository.findById(commentId).orElseThrow(NoCommentException::new);
        comment.updateContent(account, commentDto.toComment());
        commentRepository.save(comment);
    }

    @Transactional
    public void deleteComment(Long commentId, CommentDto commentDto, SessionAccountDto sessionAccountDto) {
        Account account = accountRepository.findById(sessionAccountDto.getId()).orElseThrow(NoAccountException::new);
        Comment comment = commentRepository.findById(commentId).orElseThrow(NoCommentException::new);
        comment.delete(account);
        commentRepository.save(comment);
    }
}
