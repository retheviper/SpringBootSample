package com.retheviper.springbootsample.common.constant.message;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum BoardExceptionMessage {

    /**
     * Board already exists
     */
    E000("Board already exists."),

    /**
     * Board does not exists
     */
    E001("Board does not exists."),

    /**
     * Category already exists
     */
    E002("Category already exists."),

    /**
     * Category does not exists
     */
    E003("Category does not exists."),

    /**
     * Article already exists
     */
    E004("Article already exists"),

    /**
     * Article does not exists
     */
    E005("Article does not exists"),

    /**
     * Comment already exists
     */
    E006("Comment already exists"),

    /**
     * Comment does not exists
     */
    E007("Comment does not exists");

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
    public static BoardExceptionMessage getCode(final String message) {
        return Stream.of(BoardExceptionMessage.values()).filter(c -> c.getValue().equalsIgnoreCase(message))
                .findAny().orElseThrow();
    }
}
