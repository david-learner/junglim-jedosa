package com.jedosa.junglim.article;

import com.jedosa.junglim.account.AccountRepository;
import com.jedosa.junglim.account.domain.Account;
import com.jedosa.junglim.account.domain.SessionAccountDto;
import com.jedosa.junglim.article.domain.Article;
import com.jedosa.junglim.article.domain.Pagination;
import com.jedosa.junglim.article.dto.ArticleDto;
import com.jedosa.junglim.article.dto.ArticlesDto;
import com.jedosa.junglim.article.repository.ArticleRepository;
import com.jedosa.junglim.article.repository.ArticleSearchCondition;
import com.jedosa.junglim.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ArticleService {

    private static final Logger log = LoggerFactory.getLogger(ArticleService.class);
    private final ArticleRepository articleRepository;
    private final AccountRepository accountRepository;

    public ArticleService(ArticleRepository articleRepository, AccountRepository accountRepository) {
        this.articleRepository = articleRepository;
        this.accountRepository = accountRepository;
    }

    public List<Article> getArticles(ArticleSearchCondition condition) {
        return articleRepository.search(condition);
    }

    public ArticlesDto getArticlesOfBlockWithPagination(ArticleSearchCondition condition) {
        Integer page = condition.getPage();
        Pageable pageable = PageRequest.of(page, Pagination.BLOCK_SIZE);
        if (condition.isCoverSample()) {
            pageable = PageRequest.of(page, Pagination.GALLERY_BLOCK_SIZE);
        }
        Page<Article> articlesPage = articleRepository.search(condition, pageable);
        List<Article> articles = articlesPage.getContent();
        Pagination pagination = new Pagination(articlesPage);

        List<ArticleDto> articleDtos = new ArrayList<>();
        long topBoardArticleNumber = Pagination.calculateTopBoardArticleNumber(page, articlesPage.getTotalElements());
        // ????????? ????????? ???????????? ????????? ????????????
        // DB????????? ????????? ????????? ????????? ?????? DTO??? ?????????
        for (int index = 1; index <= articles.size(); index++) {
            Article article = articles.get(index - 1);
            ArticleDto articleDto = new ArticleDto(article);
            articleDto.setBoardArticleId(topBoardArticleNumber);
            articleDtos.add(articleDto);
            topBoardArticleNumber--;
        }

        return new ArticlesDto(articleDtos, pagination);
    }

    public List<ArticleDto> getNotices(ArticleSearchCondition condition) {
        List<Article> articles = articleRepository.search(condition);
        return articles.stream().map(ArticleDto::new).collect(Collectors.toList());
    }

    /**
     *  ????????? ?????? 
     */
    @Transactional
    public Article writeArticle(ArticleDto articleDto, SessionAccountDto sessionAccountDto) {
        // ???????????? ??????
        if(articleDto.getIsNotice()) {
            if (!sessionAccountDto.isAdmin()) {
                throw new NotAdminException();
            }
            Long noticeCount = articleRepository.count(ArticleSearchCondition.ofNotice(articleDto.getBoardId()));
            if (noticeCount >= ArticleSearchCondition.NOTICE_LIMIT) {
                throw new OverNoticeCountException();
            }
        }

        // ?????? ??? ??????
        Account account = accountRepository.findById(sessionAccountDto.getId()).orElseThrow(NoAccountException::new);
        Article article = articleDto.toArticle(account, articleDto.getBoardId());
        return articleRepository.save(article);
    }

    /**
     * ???????????? ????????? ??? ??????
     */
    public ArticleDto getArticle(Long id, SessionAccountDto sessionAccountDto) {
        Article article = articleRepository.findById(id).orElseThrow(NoArticleException::new);
        if (sessionAccountDto == null) {
            return new ArticleDto(article);
        }
        return new ArticleDto(article, sessionAccountDto.getId());
    }

    /**
     * ???????????? ????????? ?????? ????????? ???????????? ?????? ??? ??? ??????
     */
    public ArticleDto getOwnArticle(Long id, SessionAccountDto sessionAccountDto) {
        Article article = articleRepository.findById(id).orElseThrow(NoArticleException::new);
        Account account = accountRepository.findById(sessionAccountDto.getId()).orElseThrow(NoLoginException::new);
        if (!article.isNotice() && !article.isOwn(account)) {
            throw new NotOwnException();
        }
        return new ArticleDto(article, account);
    }

    @Transactional
    public ArticleDto updateArticle(Long articleId, ArticleDto articleDto, SessionAccountDto sessionAccountDto) {
        Account account = accountRepository.findById(sessionAccountDto.getId()).orElseThrow(NoAccountException::new);
        Article article = articleRepository.findById(articleId).orElseThrow(NoArticleException::new);
        if (!article.isOwn(account)) {
            throw new NotOwnException();
        }
        article.updateFromArticleDto(articleDto, account);
        Article savedArticle = articleRepository.save(article);
        return new ArticleDto(savedArticle, account.getId());
    }

    @Transactional
    public void deleteArticle(Long articleId, SessionAccountDto sessionAccountDto) {
        Account account = accountRepository.findById(sessionAccountDto.getId()).orElseThrow(NoAccountException::new);
        Article article = articleRepository.findById(articleId).orElseThrow(NoArticleException::new);
        if (!article.isOwn(account)) {
            throw new NotOwnException();
        }
        article.delete();
        articleRepository.save(article);
    }
}
