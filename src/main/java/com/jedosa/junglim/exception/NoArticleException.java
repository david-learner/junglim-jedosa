package com.jedosa.junglim.exception;

public class NoArticleException extends RuntimeException {

    public NoArticleException() {
        super("존재하지 않는 글입니다");
    }
}
