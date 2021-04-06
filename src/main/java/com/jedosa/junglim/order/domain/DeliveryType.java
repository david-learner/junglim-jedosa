package com.jedosa.junglim.order.domain;

public enum DeliveryType {
    PARCEL("택배"),
    VISIT("방문수령");

    private String name;

    DeliveryType(String name) {
        this.name = name;
    }
}
