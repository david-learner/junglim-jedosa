package com.jedosa.junglim.exception;

public class NotOwnException extends RuntimeException {

    public NotOwnException() {
        super("본인 소유가 아닙니다");
    }
}
