package com.retheviper.springbootsample.api.v1.viewmodel.board;

import java.io.Serializable;

import lombok.Data;

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
