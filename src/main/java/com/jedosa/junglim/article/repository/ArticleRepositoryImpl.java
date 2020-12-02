package com.jedosa.junglim.article.repository;

import com.jedosa.junglim.article.domain.Article;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.jedosa.junglim.article.domain.QArticle.article;

public class ArticleRepositoryImpl implements ArticleRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    public ArticleRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public Page<Article> search(ArticleSearchCondition condition, Pageable pageable) {
        QueryResults<Article> results = jpaQueryFactory
                .selectFrom(article)
                .where(article.deleted.eq(condition.getIsDeleted())
                        .and(article.boardId.eq(condition.getBoardId())))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(article.id.desc())
                .fetchResults();

        List<Article> content = results.getResults();
        Long total = results.getTotal();
        return new PageImpl<>(content, pageable, total);
    }
}
