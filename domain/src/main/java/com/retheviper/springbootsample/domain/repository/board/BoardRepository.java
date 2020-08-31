package com.retheviper.springbootsample.domain.repository.board;

import com.retheviper.springbootsample.domain.entity.board.Board;
import org.springframework.data.repository.CrudRepository;

/**
 * Board category repository.
 *
 * @author retheviper
 */
public interface BoardRepository extends CrudRepository<Board, Long> {

    boolean existsByName(String name);
}
