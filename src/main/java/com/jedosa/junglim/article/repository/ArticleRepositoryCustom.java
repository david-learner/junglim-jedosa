package com.jedosa.junglim.article.repository;

import com.jedosa.junglim.article.domain.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArticleRepositoryCustom {
    Page<Article> search(ArticleSearchCondition condition, Pageable pageable);
//    List<ArticleDto> search(ArticleSearchCondition condition);
}
