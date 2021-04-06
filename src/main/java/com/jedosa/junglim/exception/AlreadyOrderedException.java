package com.jedosa.junglim.exception;

public class AlreadyOrderedException extends RuntimeException {

    public AlreadyOrderedException() {
        super("이미 주문되었습니다");
    }
}
