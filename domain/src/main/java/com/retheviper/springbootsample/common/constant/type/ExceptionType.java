package com.retheviper.springbootsample.common.constant.type;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum for exception type.
 *
 * @author retheviper
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ExceptionType {

    /**
     * Exception type: Member
     */
    T001("Member"),

    /**
     * Exception type: Authentication
     */
    T002("Authentication");

    /**
     * Actual value
     */
    private final String value;
}
