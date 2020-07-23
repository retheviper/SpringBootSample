package com.retheviper.springbootsample.application.service.board;

import java.util.List;

import com.retheviper.springbootsample.application.dto.board.CommentDto;

/**
 * Article comment service class.
 *
 * @author retheviper
 */
public interface CommentService {

    /**
     * Get list of comments.
     *
     * @return List of comment DTO
     */
    List<CommentDto> listComment(long articleId);

    /**
     * Get single comment by comment's ID.
     *
     * @param id comment's ID
     * @return Comment DTO
     */
    CommentDto getComment(long id);

    /**
     * Create new comment.
     *
     * @param dto Comment DTO
     * @return Comment DTO
     */
    CommentDto createComment(CommentDto dto);

    /**
     * Update existing comment.
     *
     * @param dto Comment DTO
     * @return Comment DTO
     */
    CommentDto updateComment(CommentDto dto);

    /**
     * Delete existing comment.
     *
     * @param name comment's ID
     */
    void deleteComment(long id);

    /**
     * Check existing by ID.
     *
     * @param id ID
     * @return result of check
     */
    void checkExists(long id);
}
