package com.retheviper.springbootsample.api.v1.form.board;

import java.io.Serializable;

import lombok.Data;

@Data
public class CategoryForm implements Serializable {

    /**
     * Category's name
     */
    private String name;

}
