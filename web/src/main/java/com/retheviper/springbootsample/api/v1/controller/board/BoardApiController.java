package com.retheviper.springbootsample.api.v1.controller.board;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.retheviper.springbootsample.api.v1.form.board.BoardForm;
import com.retheviper.springbootsample.api.v1.viewmodel.board.BoardViewModel;
import com.retheviper.springbootsample.application.dto.board.BoardDto;
import com.retheviper.springbootsample.application.service.board.BoardService;

import lombok.RequiredArgsConstructor;

/**
 * Board controller class.
 *
 * @author retheviper
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/web/board")
public class BoardApiController {

    /**
     * Pattern for check string is numeric
     */
    private final Pattern pattern;

    /**
     * Data model converter
     */
    private final ModelMapper mapper;

    /**
     * Board service
     */
    private final BoardService service;

    /**
     * Get list of Boards.
     *
     * @return list of boards
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BoardViewModel> listBoard() {
        return this.service.listBoard().stream().map(this::createViewModel)
                .collect(Collectors.toList());
    }

    /**
     * Get single board.
     *
     * @param boardId board ID
     * @return requested single board
     */
    @GetMapping("/{boardId}")
    @ResponseStatus(HttpStatus.OK)
    public BoardViewModel getBoard(@PathVariable final String boardId) {
        final BoardDto dto = new BoardDto();
        if (pattern.matcher(boardId).matches()) {
            dto.setId(Long.parseLong(boardId));
        } else {
            dto.setName(boardId);
        }
        return createViewModel(this.service.getBoard(dto));
    }

    /**
     * Create single board.
     *
     * @param form request form
     * @return created single board
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BoardViewModel createBoard(@RequestBody final BoardForm form) {
        return createViewModel(this.service.createBoard(this.mapper.map(form, BoardDto.class)));
    }

    /**
     * Update single board.
     *
     * @param boardId board ID
     * @param form request form
     * @return updated single board
     */
    @PutMapping("/{boardId}")
    @ResponseStatus(HttpStatus.OK)
    public BoardViewModel updateBoard(@PathVariable final long boardId, @RequestBody final BoardForm form) {
        final BoardDto dto = this.mapper.map(form, BoardDto.class);
        dto.setId(boardId);
        return createViewModel(this.service.updateBoard(dto));
    }

    /**
     * Delete existing single board.
     *
     * @param boardId boad ID
     */
    @DeleteMapping("/{boardId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBoard(@PathVariable final long boardId) {
        this.service.deleteBoard(boardId);
    }

    /**
     * Create view model.
     *
     * @param dto Target DTO
     * @return Generated view model
     */
    private BoardViewModel createViewModel(final BoardDto dto) {
        return this.mapper.map(dto, BoardViewModel.class);
    }

}
