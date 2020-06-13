package com.retheviper.springbootsample.common.exception;

/**
 * Custom exception class for member type.
 *
 * @author retheviper
 */
public class MemberException extends RuntimeException {

    public MemberException() {
        super();
    }

    public MemberException(final String message) {
        super(message);
    }

    public MemberException(final String message, final Throwable throwable) {
        super(message, throwable);
    }
}
