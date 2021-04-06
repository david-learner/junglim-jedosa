package com.jedosa.junglim.item.domain.option;

public enum ColorType {

    GRAY_SCALE("흑백"),
    COLOR("컬러");

    private String name;

    ColorType(String name) {
        this.name = name;
    }
}
