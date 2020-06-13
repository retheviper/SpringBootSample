package com.retheviper.springbootsample.common.constant.type;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Enum for header type.
 *
 * @author retheviper
 */
@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum HeaderType {

    /**
     * token for authentication.
     */
    AUTH_TOKEN("X-AUTH-TOKEN");

    /**
     * Actual value
     */
    private final String value;
}
