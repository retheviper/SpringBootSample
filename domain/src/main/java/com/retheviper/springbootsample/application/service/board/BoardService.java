package com.retheviper.springbootsample.application.service.board;

import com.retheviper.springbootsample.application.dto.board.BoardDto;

import java.util.List;

/**
 * Board service class.
 *
 * @author retheviper
 */
public interface BoardService {

    /**
     * Get list of boards.
     *
     * @return List of board DTO
     */
    List<BoardDto> listBoard();

    /**
     * Get single board.
     *
     * @param dto Board DTO
     * @return Board DTO
     */
    BoardDto getBoard(BoardDto dto);

    /**
     * Create new board.
     *
     * @param dto Board DTO
     * @return Board DTO
     */
    BoardDto createBoard(BoardDto dto);

    /**
     * Update existing board.
     *
     * @param dto Board DTO
     * @return Board DTO
     */
    BoardDto updateBoard(BoardDto dto);

    /**
     * Delete existing board.
     *
     * @param id board's ID
     */
    void deleteBoard(long id);

    /**
     * Check existing by ID.
     *
     * @param id ID
     * @return result of check
     */
    void checkExists(long id);
}
