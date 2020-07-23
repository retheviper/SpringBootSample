package com.retheviper.springbootsample.application.service.board;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.retheviper.springbootsample.application.dto.board.CategoryDto;
import com.retheviper.springbootsample.common.exception.BoardException;
import com.retheviper.springbootsample.domain.entity.board.Category;
import com.retheviper.springbootsample.domain.repository.board.CategoryRepository;

import lombok.RequiredArgsConstructor;

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
        return createDto(dto.getName() != null ? getEntity(dto.getName()) : getEntity(dto.getId()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public CategoryDto createCategory(final CategoryDto dto) {
        if (isExistingInBoard(dto)) {
            throw new BoardException("Category Already Exists");
        }
        return save(this.mapper.map(dto, Category.class));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public CategoryDto updateCategory(final CategoryDto dto) {
        if (!this.repository.existsById(dto.getId())) {
            throw new BoardException("Category not Exists");
        }
        if (isExistingInBoard(dto)) {
            throw new BoardException("Category Already Exists");
        }
        final Category entity = getEntity(dto.getId());
        this.mapper.map(dto, entity);
        return save(entity);
    }

    /**
     * {@inheritDoc}
     */
    public void checkExists(final long id) {
        if (!this.repository.existsById(id)) {
            throw new BoardException("Category not exists");
        }
    }

    /**
     * Check new category's name exists in specific board.
     *
     * @param dto category DTO
     * @return result of check
     */
    private boolean isExistingInBoard(final CategoryDto dto) {
        return this.repository.existsByNameAndBoardIdIs(dto.getName(), dto.getBoard().getId());
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
        return this.repository.findById(id).orElseThrow(NullPointerException::new);
    }

    /**
     * Get entity from repository.
     *
     * @param name Category's name
     * @return Category entity
     */
    private Category getEntity(final String name) {
        return this.repository.findByNameContaining(name).orElseThrow(NullPointerException::new);
    }

}
