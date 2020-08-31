package com.retheviper.springbootsample.api.v1.form.board;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

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
