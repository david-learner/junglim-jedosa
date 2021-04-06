package com.jedosa.junglim.order.repository;

import com.jedosa.junglim.order.domain.OrderItem;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.util.List;

import static com.jedosa.junglim.order.domain.QOrderItem.*;

public class OrderItemRepositoryImpl implements OrderItemRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    public OrderItemRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<OrderItem> search(OrderItemSearchCondition condition) {
        QueryResults<OrderItem> orderItemQueryResults = jpaQueryFactory
                .select(orderItem)
                .from(orderItem)
                .where(orderItem.order.id.eq(condition.getOrderId())
                        .and(orderItem.isInCart.eq(condition.getIsInCart()))
                        .and(orderItem.isDeleted.eq(condition.getIsDeleted())))
                .fetchResults();

        return orderItemQueryResults.getResults();
    }
}
