package com.retheviper.springbootsample.application.dto.board;

import java.io.Serializable;

import lombok.Data;

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
