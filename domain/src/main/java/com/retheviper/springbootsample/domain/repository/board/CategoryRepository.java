package com.retheviper.springbootsample.domain.repository.board;

import com.retheviper.springbootsample.domain.entity.board.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Article category repository.
 *
 * @author retheviper
 */
public interface CategoryRepository extends CrudRepository<Category, Long> {

    List<Category> findByBoardIdIs(long boardId);

    boolean existsByNameAndBoardIdIs(String name, long boardId);
}
