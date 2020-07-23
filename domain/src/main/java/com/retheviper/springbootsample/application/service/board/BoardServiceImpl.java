package com.retheviper.springbootsample.application.service.board;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.retheviper.springbootsample.application.dto.board.BoardDto;
import com.retheviper.springbootsample.common.exception.BoardException;
import com.retheviper.springbootsample.domain.entity.board.Board;
import com.retheviper.springbootsample.domain.repository.board.BoardRepository;

import lombok.RequiredArgsConstructor;

/**
 * Board service class. (Implementation)
 *
 * @author retheviper
 */
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    /**
     * Data model converter
     */
    private final ModelMapper mapper;

    /**
     * Board repository
     */
    private final BoardRepository repository;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<BoardDto> listBoard() {
        return StreamSupport.stream(this.repository.findAll().spliterator(), false)
                .map(this::createDto)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public BoardDto getBoard(final BoardDto dto) {
        return createDto(dto.getName() != null ? getEntity(dto.getName()) : getEntity(dto.getId()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public BoardDto createBoard(final BoardDto dto) {
        if (isExisting(dto)) {
            throw new BoardException("Board exists");
        }
        return save(this.mapper.map(dto, Board.class));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public BoardDto updateBoard(final BoardDto dto) {
        if (!isExisting(dto)) {
            throw new BoardException("Board not exists");
        }
        final Board entity = getEntity(dto.getId());
        this.mapper.map(dto, entity);
        return save(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void deleteBoard(final long id) {
        this.repository.deleteById(id);
    }

    /**
     * {@inheritDoc}
     */
    public void checkExists(final long id) {
        if (!this.repository.existsById(id)) {
            throw new BoardException("Board not exists");
        }
    }

    /**
     * Check new board name exists.
     *
     * @param dto category DTO
     * @return result of check
     */
    private boolean isExisting(final BoardDto dto) {
        return this.repository.existsByName(dto.getName()) || this.repository.existsById(dto.getId());
    }

    /**
     * Save entity to repository and get result.
     *
     * @param entity enitity to save
     * @return Saved result as DTO
     */
    private BoardDto save(final Board entity) {
        return this.createDto(this.repository.save(entity));
    }

    /**
     * Map entity to DTO.
     *
     * @param entity Board entity
     * @return Board DTO
     */
    private BoardDto createDto(final Board entity) {
        return this.mapper.map(entity, BoardDto.class);
    }

    /**
     * Get entity from repository.
     *
     * @param id Board's id
     * @return Board entity
     */
    private Board getEntity(final long id) {
        return this.repository.findById(id).orElseThrow(BoardException::new);
    }

    /**
     * Get entity from repository.
     *
     * @param name Board's name
     * @return Board entity
     */
    private Board getEntity(final String name) {
        return this.repository.findByNameContaining(name).orElseThrow(BoardException::new);
    }

}
