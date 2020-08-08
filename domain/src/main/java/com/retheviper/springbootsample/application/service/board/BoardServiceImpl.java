package com.retheviper.springbootsample.application.service.board;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.retheviper.springbootsample.application.dto.board.BoardDto;
import com.retheviper.springbootsample.common.constant.message.BoardExceptionMessage;
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
        return createDto(getEntity(dto.getId()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public BoardDto createBoard(final BoardDto dto) {
        checkAlreadyExists(dto.getName());
        return save(this.mapper.map(dto, Board.class));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public BoardDto updateBoard(final BoardDto dto) {
        checkExists(dto.getId());
        checkAlreadyExists(dto.getName());
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
            throw new BoardException(BoardExceptionMessage.E001.getValue());
        }
    }

    /**
     * Check board name can be created.
     *
     * @param name board name
     */
    private void checkAlreadyExists(final String name) {
        if (this.repository.existsByName(name)) {
            throw new BoardException(BoardExceptionMessage.E000.getValue());
        }
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

}
