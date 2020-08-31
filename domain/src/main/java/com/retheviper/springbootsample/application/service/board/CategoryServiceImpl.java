package com.retheviper.springbootsample.application.service.board;

import com.retheviper.springbootsample.application.dto.board.CategoryDto;
import com.retheviper.springbootsample.common.constant.message.BoardExceptionMessage;
import com.retheviper.springbootsample.common.exception.BoardException;
import com.retheviper.springbootsample.domain.entity.board.Category;
import com.retheviper.springbootsample.domain.repository.board.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Article category service class. (Implementation)
 *
 * @author retheviper
 */
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    /**
     * Data model converter
     */
    private final ModelMapper mapper;

    /**
     * Category repository
     */
    private final CategoryRepository repository;

    /**
     * Board service
     */
    private final BoardService boardService;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<CategoryDto> listCategory(final long boardId) {
        return StreamSupport.stream(this.repository.findByBoardIdIs(boardId).spliterator(), false)
                .map(this::createDto)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public CategoryDto getCategory(final CategoryDto dto) {
        return createDto(getEntity(dto.getId()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public CategoryDto createCategory(final CategoryDto dto) {
        checkAlreadyExists(dto);
        return save(this.mapper.map(dto, Category.class));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public CategoryDto updateCategory(final CategoryDto dto) {
        checkExists(dto.getId());
        checkAlreadyExists(dto);
        dto.setBoard(this.boardService.getBoard(dto.getBoard()));
        final Category entity = getEntity(dto.getId());
        this.mapper.map(dto, entity);
        return save(entity);
    }

    /**
     * {@inheritDoc}
     */
    public void checkExists(final long id) {
        if (!this.repository.existsById(id)) {
            throw new BoardException(BoardExceptionMessage.E003.getValue());
        }
    }

    /**
     * Check category's name can be created in specific board.
     *
     * @param dto category DTO
     * @return result of check
     */
    private void checkAlreadyExists(final CategoryDto dto) {
        if (this.repository.existsByNameAndBoardIdIs(dto.getName(), dto.getBoard().getId())) {
            throw new BoardException(BoardExceptionMessage.E002.getValue());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void deleteCategory(final long id) {
        this.repository.deleteById(id);
    }

    /**
     * Save entity to repository and get result.
     *
     * @param entity enitity to save
     * @return Saved result as DTO
     */
    private CategoryDto save(final Category entity) {
        return this.createDto(this.repository.save(entity));
    }

    /**
     * Map entity to DTO.
     *
     * @param entity Board entity
     * @return Category DTO
     */
    private CategoryDto createDto(final Category entity) {
        return this.mapper.map(entity, CategoryDto.class);
    }

    /**
     * Get entity from repository.
     *
     * @param id Category's ID
     * @return Category entity
     */
    private Category getEntity(final long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new BoardException(BoardExceptionMessage.E003.getValue()));
    }
}
