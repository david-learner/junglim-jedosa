package com.jedosa.junglim.exception;

public class NoCommentException extends RuntimeException {
    public NoCommentException() {
        super("존재하지 않는 댓글입니다");
    }
}
