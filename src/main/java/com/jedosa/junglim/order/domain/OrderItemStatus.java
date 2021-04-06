package com.jedosa.junglim.order.domain;

public enum OrderItemStatus {
    CART("카트"),
    ORDER("주문");

    private String status;

    OrderItemStatus(String status) {
        this.status = status;
    }
}
