package com.jedosa.junglim.exception;

public class NoAccountException extends RuntimeException {

    public NoAccountException() {
        super("존재하지 않는 회원입니다");
    }
}
