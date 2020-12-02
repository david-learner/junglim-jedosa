package com.jedosa.junglim.article;

import com.jedosa.junglim.account.AccountRepository;
import com.jedosa.junglim.article.domain.Article;
import com.jedosa.junglim.article.domain.Pagination;
import com.jedosa.junglim.article.dto.ArticleDto;
import com.jedosa.junglim.article.dto.ArticlesDto;
import com.jedosa.junglim.article.repository.ArticleRepository;
import com.jedosa.junglim.article.repository.ArticleSearchCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {

    private static final Logger log =  LoggerFactory.getLogger(ArticleService.class);
    private final ArticleRepository articleRepository;
    private final AccountRepository accountRepository;

    public ArticleService(ArticleRepository articleRepository, AccountRepository accountRepository) {
        this.articleRepository = articleRepository;
        this.accountRepository = accountRepository;
    }

    public ArticlesDto getArticlesOfBlockWithPagination(Integer currentPage, ArticleSearchCondition condition) {

        Pageable pageable = PageRequest.of(currentPage, Pagination.BLOCK_SIZE);
        Page<Article> articlesPage = articleRepository.search(condition, pageable);
        List<Article> articles = articlesPage.getContent();
        Pagination pagination = new Pagination(articlesPage);

        List<ArticleDto> articleDtos = new ArrayList<>();
        long topBoardArticleNumber = Pagination.calculateTopBoardArticleNumber(currentPage, articlesPage.getTotalElements());
        // 게시글 순서를 나타내는 번호를 만들어서
        // DB로부터 불러온 데이터 순서에 맞게 DTO에 넣는다
        for (int index = 1; index <= articles.size(); index++) {
            Article article = articles.get(index - 1);
            ArticleDto articleDto = new ArticleDto(article);
            articleDto.setBoardArticleId(topBoardArticleNumber);
            articleDtos.add(articleDto);
            topBoardArticleNumber--;
        }

        return new ArticlesDto(articleDtos, pagination);
    }
}
