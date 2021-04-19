package com.jedosa.junglim.order.repository;

import com.jedosa.junglim.order.domain.Order;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.jedosa.junglim.order.domain.QOrder.order;

public class OrderRepositoryImpl implements OrderRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    public OrderRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public Page<Order> search(OrderSearchCondition condition, Pageable pageable) {
        QueryResults<Order> results = jpaQueryFactory
                .select(order)
                .from(order)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(order.id.desc())
                .fetchResults();
        List<Order> content = results.getResults();
        long total = results.getTotal();
        return new PageImpl<>(content, pageable, total);
    }

    @Override
    public List<Order> findByLimit(Integer limit) {
        return jpaQueryFactory
                .select(order)
                .from(order)
                .limit(limit)
                .fetchResults()
                .getResults();
    }
}
