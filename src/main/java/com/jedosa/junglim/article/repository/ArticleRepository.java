package com.jedosa.junglim.article.repository;

import com.jedosa.junglim.article.domain.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long>, ArticleRepositoryCustom {
    Page<Article> findAllByBoardId(Long boardId, Pageable pageable);
}
