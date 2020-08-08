package com.retheviper.springbootsample.api.v1.controller.board;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.retheviper.springbootsample.api.v1.form.board.CategoryForm;
import com.retheviper.springbootsample.api.v1.viewmodel.board.CategoryViewModel;
import com.retheviper.springbootsample.application.dto.board.BoardDto;
import com.retheviper.springbootsample.application.dto.board.CategoryDto;
import com.retheviper.springbootsample.application.service.board.CategoryService;
import com.retheviper.springbootsample.common.constant.MemberRole;

import lombok.RequiredArgsConstructor;

/**
 * Article category controller class.
 *
 * @author retheviper
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/web/boards/{boardId}/categories")
public class CategoryApiController {

    /**
     * Data model converter
     */
    private final ModelMapper mapper;

    /**
     * Board category service
     */
    private final CategoryService service;

    /**
     * Get list of categories.
     *
     * @return list of categories
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryViewModel> listCategory(@PathVariable final long boardId) {
        return this.service.listCategory(boardId).stream().map(this::createViewModel)
                .collect(Collectors.toList());
    }

    /**
     * Get single category.
     *
     * @param categoryId category ID
     * @return requested single category
     */
    @GetMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryViewModel getCategory(@PathVariable final Long categoryId) {
        final CategoryDto dto = new CategoryDto();
        dto.setId(categoryId);
        return createViewModel(this.service.getCategory(dto));
    }

    /**
     * Create single category.
     *
     * @param form request form
     * @return created single category
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Secured(MemberRole.ROLE_ADMIN)
    public CategoryViewModel createCategory(@PathVariable final long boardId,
            @Validated @RequestBody final CategoryForm form) {
        final CategoryDto dto = this.mapper.map(form, CategoryDto.class);
        final BoardDto board = new BoardDto();
        board.setId(boardId);
        dto.setBoard(board);
        return createViewModel(this.service.createCategory(dto));
    }

    /**
     * Update single category.
     *
     * @param categoryId category ID
     * @param form request form
     * @return updated single category
     */
    @PutMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    @Secured(MemberRole.ROLE_ADMIN)
    public CategoryViewModel updateCategory(@PathVariable final long boardId, @PathVariable final long categoryId,
            @Validated @RequestBody final CategoryForm form) {
        final CategoryDto dto = this.mapper.map(form, CategoryDto.class);
        dto.setId(categoryId);
        final BoardDto board = new BoardDto();
        board.setId(boardId);
        dto.setBoard(board);
        return createViewModel(this.service.updateCategory(dto));
    }

    /**
     * Delete existing single category.
     *
     * @param commentId category ID
     */
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{categoryId}")
    @Secured(MemberRole.ROLE_ADMIN)
    public void deleteCategory(@PathVariable final long categoryId) {
        this.service.deleteCategory(categoryId);
    }

    /**
     * Create view model.
     *
     * @param dto target DTO
     * @return generated view model
     */
    private CategoryViewModel createViewModel(final CategoryDto dto) {
        return this.mapper.map(dto, CategoryViewModel.class);
    }

}
