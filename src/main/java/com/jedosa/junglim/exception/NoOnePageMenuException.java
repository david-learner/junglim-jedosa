package com.jedosa.junglim.exception;

/**
 *  DB에 원페이지 메뉴 데이터가 없을 때 발생한다
 */
public class NoOnePageMenuException extends RuntimeException {

    public NoOnePageMenuException() {
        super("메뉴 페이지 정보를 읽어올 수 없습니다");
    }
}
