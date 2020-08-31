package com.retheviper.springbootsample.application.service.board;

import com.retheviper.springbootsample.application.dto.board.CategoryDto;

import java.util.List;

/**
 * Article category service class.
 *
 * @author retheviper
 */
public interface CategoryService {

    /**
     * Get list of categories.
     *
     * @param id board's ID
     * @return List of category DTO
     */
    List<CategoryDto> listCategory(long boardId);

    /**
     * Get single category by category DTO.
     *
     * @param dto Category DTO
     * @return Category DTO
     */
    CategoryDto getCategory(CategoryDto dto);

    /**
     * Create new category.
     *
     * @param dto Category DTO
     * @return Category DTO
     */
    CategoryDto createCategory(CategoryDto dto);

    /**
     * Update existing category.
     *
     * @param dto Category DTO
     * @return Category DTO
     */
    CategoryDto updateCategory(CategoryDto dto);

    /**
     * Delete existing category.
     *
     * @param id category's ID
     */
    void deleteCategory(long id);

    /**
     * Check existing by ID.
     *
     * @param id ID
     * @return result of check
     */
    void checkExists(long id);
}
