package com.jedosa.junglim.article.repository;

import com.jedosa.junglim.article.domain.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticleRepositoryCustom {
    Page<Article> search(ArticleSearchCondition condition, Pageable pageable);
    List<Article> search(ArticleSearchCondition condition);
    Long count(ArticleSearchCondition condition);
//    List<ArticleDto> search(ArticleSearchCondition condition);
}
