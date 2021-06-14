package com.jedosa.junglim.order.domain;

import lombok.Getter;

@Getter
public enum DeliveryType {
    PARCEL("택배"),
    VISIT("방문수령");

    private String labelForKorean;

    DeliveryType(String labelForKorean) {
        this.labelForKorean = labelForKorean;
    }
}
