package com.jedosa.junglim.exception;

public class NoFlyleafContentPriceException extends RuntimeException {

    public NoFlyleafContentPriceException() {
        super("간지 문구 출력 가격이 지정되지 않았습니다");
    }
}
