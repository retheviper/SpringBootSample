package com.retheviper.springbootsample.common.exception;

public class BoardException extends RuntimeException {

    public BoardException() {
        super();
    }

    public BoardException(final String message) {
        super(message);
    }

    public BoardException(final String message, final Throwable throwable) {
        super(message, throwable);
    }
}