package com.retheviper.springbootsample.api.v1.form.board;

import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryForm implements Serializable {

    /**
     * Category's name
     */
    private String name;

}
