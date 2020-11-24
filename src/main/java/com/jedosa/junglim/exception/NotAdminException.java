package com.jedosa.junglim.exception;

public class NotAdminException extends RuntimeException {

    public NotAdminException() {
        super("관리자만 접근할 수 있습니다");
    }
}
