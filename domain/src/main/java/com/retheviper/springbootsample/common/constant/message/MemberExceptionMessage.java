package com.retheviper.springbootsample.common.constant.message;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum MemberExceptionMessage {

    /**
     * Data is null
     */
    E000("None data Exists."),

    /**
     * Password is incorrect
     */
    E001("Password unmatches."),

    /**
     * Member ID already exists
     */
    E002("ID already exists."),

    /**
     * Member ID does not exists
     */
    E003("ID does not exists."),

    /**
     * Member ID does not mathces
     */
    E004("User unmatches");

    /**
     * Actual value
     */
    private final String value;

    /**
     * Get Exception Code from Exception Message.
     *
     * @param message exception message
     * @return found code
     */
    public static MemberExceptionMessage getCode(final String message) {
        return Stream.of(MemberExceptionMessage.values()).filter(c -> c.getValue().equalsIgnoreCase(message))
                .findAny().orElseThrow();
    }
}
