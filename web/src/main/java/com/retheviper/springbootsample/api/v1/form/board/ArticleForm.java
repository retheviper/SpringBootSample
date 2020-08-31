package com.retheviper.springbootsample.api.v1.form.board;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class ArticleForm implements Serializable {

    /**
     * Article's category
     */
    private long categoryId;

    /**
     * Article's title
     */
    @NotEmpty
    private String title;

    /**
     * Article's content
     */
    @NotEmpty
    private String content;
}
