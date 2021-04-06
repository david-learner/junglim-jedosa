package com.jedosa.junglim.order.domain;

public enum OrderStatus {
    ORDER_WAITING("주문대기"),
    ORDER_COMPLETE("주문완료"),
    MAKING_WAITING("제작대기"),
    MAKING("제작진행중"),
    MADE("제작완료"),
    DELIVERY_WAITING("배송준비"),
    DELIVERING("배송중"),
    DELIVERED("배송완료");

    private String name;

    OrderStatus(String name) {
        this.name = name;
    }
}
