package com.retheviper.springbootsample.application.dto.board;

import lombok.Data;

import java.io.Serializable;

/**
 * Board DTO class.
 *
 * @author retheviper
 */
@Data
public class BoardDto implements Serializable {

    /**
     * Board ID
     */
    private long id;

    /**
     * Board's name
     */
    private String name;

    /**
     * Board's description
     */
    private String description;
}
