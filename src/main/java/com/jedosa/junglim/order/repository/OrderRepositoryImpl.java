package com.jedosa.junglim.order.repository;

import com.jedosa.junglim.order.domain.Order;
import com.jedosa.junglim.order.domain.QOrder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.util.List;

import static com.jedosa.junglim.order.domain.QOrder.order;

public class OrderRepositoryImpl implements OrderRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;

    public OrderRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
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
