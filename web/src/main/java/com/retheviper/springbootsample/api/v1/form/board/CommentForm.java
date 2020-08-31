package com.retheviper.springbootsample.api.v1.form.board;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class CommentForm implements Serializable {

    /**
     * Article's content
     */
    @NotEmpty
    private String content;
}
