package com.retheviper.springbootsample.domain.repository.board;

import com.retheviper.springbootsample.domain.entity.board.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

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
