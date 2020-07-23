package com.retheviper.springbootsample.domain.repository.board;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.retheviper.springbootsample.domain.entity.board.Comment;

/**
 * Article comment repository.
 *
 * @author retheviper
 */
public interface CommentRepository extends CrudRepository<Comment, Long> {

    /**
     * Search articles with article ID.
     *
     * @param articleId article ID
     * @return result of search
     */
    List<Comment> findAllByArticleId(long articleId);
}
