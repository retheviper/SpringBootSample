package com.retheviper.springbootsample.api.v1.controller.board;

import com.retheviper.springbootsample.api.v1.form.board.BoardForm;
import com.retheviper.springbootsample.api.v1.viewmodel.board.BoardViewModel;
import com.retheviper.springbootsample.application.dto.board.BoardDto;
import com.retheviper.springbootsample.application.service.board.BoardService;
import com.retheviper.springbootsample.common.constant.MemberRole;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Board controller class.
 *
 * @author retheviper
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/web/boards")
public class BoardApiController {

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
    public BoardViewModel getBoard(@PathVariable final Long boardId) {
        final BoardDto dto = new BoardDto();
        dto.setId(boardId);
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
    @Secured(MemberRole.ROLE_ADMIN)
    public BoardViewModel createBoard(@RequestBody final BoardForm form) {
        return createViewModel(this.service.createBoard(this.mapper.map(form, BoardDto.class)));
    }

    /**
     * Update single board.
     *
     * @param boardId board ID
     * @param form    request form
     * @return updated single board
     */
    @PutMapping("/{boardId}")
    @ResponseStatus(HttpStatus.OK)
    @Secured(MemberRole.ROLE_ADMIN)
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
    @Secured(MemberRole.ROLE_ADMIN)
    public void deleteBoard(@PathVariable final long boardId) {
        this.service.deleteBoard(boardId);
    }

    /**
     * Create view model.
     *
     * @param dto target DTO
     * @return generated view model
     */
    private BoardViewModel createViewModel(final BoardDto dto) {
        return this.mapper.map(dto, BoardViewModel.class);
    }

}
