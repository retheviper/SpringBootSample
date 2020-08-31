package com.retheviper.springbootsample.api.v1.viewmodel.board;

import lombok.Data;

import java.io.Serializable;

/**
 * Board comment view model class.
 *
 * @author retheviper
 */
@Data
public class BoardViewModel implements Serializable {

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
