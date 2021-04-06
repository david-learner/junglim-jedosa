package com.jedosa.junglim.payment.domain;

public enum PaymentStatus {

    WAITING("결제대기"),
    PAID("결제완료"),
    CANCELED("결제취소");

    private String name;

    PaymentStatus(String name) {
        this.name = name;
    }
}
