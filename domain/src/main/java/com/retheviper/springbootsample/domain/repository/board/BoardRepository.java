package com.retheviper.springbootsample.domain.repository.board;

import org.springframework.data.repository.CrudRepository;

import com.retheviper.springbootsample.domain.entity.board.Board;

/**
 * Board category repository.
 *
 * @author retheviper
 */
public interface BoardRepository extends CrudRepository<Board, Long> {

    boolean existsByName(String name);
}
