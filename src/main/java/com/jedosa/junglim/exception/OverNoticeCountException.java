package com.jedosa.junglim.exception;

/**
 * 개수 초과 예외
 */
public class OverNoticeCountException extends RuntimeException {

    public OverNoticeCountException() {
        super("정해진 공지 개수를 초과 하였습니다");
    }
}
