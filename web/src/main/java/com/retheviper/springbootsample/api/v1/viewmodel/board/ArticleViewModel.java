package com.retheviper.springbootsample.api.v1.viewmodel.board;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

/**
 * Article view model class.
 *
 * @author retheviper
 */
@Data
public class ArticleViewModel implements Serializable {

    /**
     * Article ID
     */
    private long id;

    /**
     * Article's title
     */
    private String title;

    /**
     * Article's content
     */
    private String content;

    /**
     * Created by
     */
    private String createdBy;

    /**
     * Category
     */
    private CategoryViewModel category;

    /**
     * Created date
     */
    private LocalDateTime createdDate;

    /**
     * Last modified date
     */
    private LocalDateTime lastModifiedDate;
}
