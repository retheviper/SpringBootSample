package com.retheviper.springbootsample.application.dto.board;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Article comment DTO class.
 *
 * @author retheviper
 */
@Data
public class CommentDto {

    /**
     * Comment's ID
     */
    private long id;

    /**
     * Article that this comment belongs
     */
    private ArticleDto article;

    /**
     * Comment's content
     */
    private String content;

    /**
     * Created by
     */
    private String createdBy;

    /**
     * Last modified date
     */
    private LocalDateTime lastModifiedDate;
}
