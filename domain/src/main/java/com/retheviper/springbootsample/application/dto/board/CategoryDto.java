package com.retheviper.springbootsample.application.dto.board;

import lombok.Data;

/**
 * Article category DTO class.
 *
 * @author retheviper
 */
@Data
public class CategoryDto {

    /**
     * Category ID;
     */
    private long id;

    /**
     * Board that this Category belongs
     */
    private BoardDto board;

    /**
     * Category's name
     */
    private String name;
}
