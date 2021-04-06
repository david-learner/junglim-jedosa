package com.jedosa.junglim.item.domain.option;

public enum PrintingType {
    SIMPLEX("단면"),
    DUPLEX("양면");

    private String name;

    PrintingType(String name) {
        this.name = name;
    }
}
