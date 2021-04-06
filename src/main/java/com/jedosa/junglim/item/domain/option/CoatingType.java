package com.jedosa.junglim.item.domain.option;

public enum CoatingType {

    NON_GLARE("무광"),
    GLARE("유광");

    private String name;

    CoatingType(String name) {
        this.name = name;
    }
}
