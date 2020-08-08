package com.retheviper.springbootsample.domain.repository.board;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.retheviper.springbootsample.domain.entity.board.Category;

/**
 * Article category repository.
 *
 * @author retheviper
 */
public interface CategoryRepository extends CrudRepository<Category, Long> {

    List<Category> findByBoardIdIs(long boardId);

    boolean existsByNameAndBoardIdIs(String name, long boardId);
}
