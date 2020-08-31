package com.retheviper.springbootsample.api.v1.viewmodel.board;

import lombok.Data;

import java.io.Serializable;

/**
 * Article category view model class.
 *
 * @author retheviper
 */
@Data
public class CategoryViewModel implements Serializable {

    /**
     * Category ID
     */
    private long id;

    /**
     * Category's name
     */
    private String name;
}
