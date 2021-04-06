package com.jedosa.junglim.article.domain;

import lombok.Getter;

@Getter
public enum Board {
    ORDER_QUESTION(1L, "주문문의"),
    COVER_SAMPLE(2L, "표지샘플"),
    NOTICE(3L, "공지사항");

    private Long id;
    private String name;

    Board(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
