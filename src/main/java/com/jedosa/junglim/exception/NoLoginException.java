package com.jedosa.junglim.exception;

public class NoLoginException extends RuntimeException {

    public NoLoginException() {
        super("NEED LOGIN");
    }
}
