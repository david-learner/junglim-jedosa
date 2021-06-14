package com.jedosa.junglim.order.domain;

public enum OrderStatus {
    ORDER_WAITING("주문대기"),
    ORDER_COMPLETE("주문완료"),
    PAY_WAITING("결제대기"),
    PAID("결제완료"),
    MAKING("제작중"),
    MADE("제작완료"),
    DELIVERY_WAITING("배송준비"),
    DELIVERING("배송중"),
    DELIVERED("배송완료");

    private String labelForKorean;

    OrderStatus(String labelForKorean) {
        this.labelForKorean = labelForKorean;
    }

    public String toLabelForKorean() {
        return this.labelForKorean;
    }
}
