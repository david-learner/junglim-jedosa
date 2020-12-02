package com.jedosa.junglim.article.domain;

import lombok.Getter;

@Getter
public enum ReplyStatus {
    YET("답변대기"),
    DONE("답변완료");

    private final String status;

    ReplyStatus(String status) {
        this.status = status;
    }
}
