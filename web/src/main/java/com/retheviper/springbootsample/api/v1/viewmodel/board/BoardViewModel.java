package com.retheviper.springbootsample.api.v1.viewmodel.board;

import java.io.Serializable;

import lombok.Data;

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
