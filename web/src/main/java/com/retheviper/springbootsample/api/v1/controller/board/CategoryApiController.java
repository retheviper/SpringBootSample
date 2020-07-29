package com.retheviper.springbootsample.api.v1.controller.board;

import java.util.List;
import java.util.regex.Pattern;
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
@RequestMapping("api/v1/web/board/{boardId}/category")
public class CategoryApiController {

    /**
     * Pattern for check string is numeric
     */
    private final Pattern pattern;

    /**
     * Data model converter
     */
    private final ModelMapper mapper;

    /**
     * Board category service
     */
    private final CategoryService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryViewModel> listCategory(@PathVariable final long boardId) {
        return this.service.listCategory(boardId).stream().map(this::createViewModel)
                .collect(Collectors.toList());
    }

    @GetMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryViewModel getCategory(@PathVariable final String categoryId) {
        final CategoryDto dto = new CategoryDto();
        if (pattern.matcher(categoryId).matches()) {
            dto.setId(Long.parseLong(categoryId));
        } else {
            dto.setName(categoryId);
        }
        return createViewModel(this.service.getCategory(dto));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Secured(MemberRole.ADMIN)
    public CategoryViewModel createCategory(@PathVariable final long boardId,
            @Validated @RequestBody final CategoryForm form) {
        final CategoryDto dto = this.mapper.map(form, CategoryDto.class);
        final BoardDto board = new BoardDto();
        board.setId(boardId);
        dto.setBoard(board);
        return createViewModel(this.service.createCategory(dto));
    }

    @PutMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    @Secured(MemberRole.ADMIN)
    public CategoryViewModel updateCategory(@PathVariable final long categoryId,
            @Validated @RequestBody final CategoryForm form) {
        final CategoryDto dto = this.mapper.map(form, CategoryDto.class);
        dto.setId(categoryId);
        return createViewModel(this.service.updateCategory(dto));
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{categoryId}")
    @Secured(MemberRole.ADMIN)
    public void deleteCategory(@PathVariable final long categoryId) {
        this.service.deleteCategory(categoryId);
    }

    /**
     * Create view model.
     *
     * @param dto Target DTO
     * @return Generated view model
     */
    private CategoryViewModel createViewModel(final CategoryDto dto) {
        return this.mapper.map(dto, CategoryViewModel.class);
    }

}
