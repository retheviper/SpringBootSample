package com.retheviper.springbootsample.api.v1.form.board;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class BoardForm implements Serializable {

    /**
     * Board's name
     */
    @NotEmpty
    private String name;

    /**
     * Board's description
     */
    private String description;
}
