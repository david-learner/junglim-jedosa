package com.jedosa.junglim.order.repository;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemSearchCondition {

    private Long orderId;
    private Boolean isInCart;
    private Boolean isDeleted;

    public static OrderItemSearchCondition of(Long orderId) {
        OrderItemSearchCondition condition = new OrderItemSearchCondition();
        condition.setOrderId(orderId);
        condition.setIsInCart(Boolean.TRUE);
        condition.setIsDeleted(Boolean.FALSE);
        return condition;
    }
}
