package com.retheviper.springbootsample.api.v1.controller.board;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.retheviper.springbootsample.api.v1.form.board.ArticleForm;
import com.retheviper.springbootsample.api.v1.viewmodel.board.ArticleViewModel;
import com.retheviper.springbootsample.application.dto.board.ArticleDto;
import com.retheviper.springbootsample.application.dto.board.BoardDto;
import com.retheviper.springbootsample.application.dto.board.CategoryDto;
import com.retheviper.springbootsample.application.service.board.ArticleService;
import com.retheviper.springbootsample.common.constant.message.BoardExceptionMessage;
import com.retheviper.springbootsample.common.exception.BoardException;

import lombok.RequiredArgsConstructor;

/**
 * Board article controller class.
 *
 * @author retheviper
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/web/boards/{boardId}/articles")
public class ArticleApiController {

    /**
     * Data model converter
     */
    private final ModelMapper mapper;

    /**
     * Board service
     */
    private final ArticleService service;

    /**
     * Page resource assembler for article
     */
    private final PagedResourcesAssembler<ArticleViewModel> assembler;

    /**
     * Get list of board articles.
     *
     * @param boardId board ID
     * @param page current page
     * @param size number of rows
     * @return paged list of board articles
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PagedModel<EntityModel<ArticleViewModel>> listArticle(@PathVariable final long boardId,
            @RequestParam final int page, @RequestParam final int size) {
        return this.assembler
                .toModel(this.service.listArticle(boardId, PageRequest.of(page, size))
                        .map(this::createViewModel));
    }

    /**
     * Get list of board articles with keyword.
     *
     * @param boardId board ID
     * @param page current page
     * @param size number of rows
     * @param keyword keyword for search
     * @param keywordType type of keyword
     * @return paged list of board articles with keyword
     */
    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public PagedModel<EntityModel<ArticleViewModel>> searchArticle(@PathVariable final long boardId,
            @RequestParam final int page,
            @RequestParam final int size,
            @RequestParam final String keyword,
            @RequestParam(required = false, defaultValue = "0") final int keywordType) {
        return this.assembler
                .toModel(this.service.listArticle(boardId, PageRequest.of(page, size), keyword, keywordType)
                        .map(this::createViewModel));
    }

    /**
     * Get single board article.
     *
     * @param articleId article's ID
     * @return requested single board article
     */
    @GetMapping("/{articleId}")
    @ResponseStatus(HttpStatus.OK)
    public ArticleViewModel getArticle(@PathVariable final long articleId) {
        return createViewModel(this.service.getArticle(articleId));
    }

    /**
     * Create single board article.
     *
     * @param boardId board ID
     * @param form request form
     * @return created single board article
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ArticleViewModel createArticle(@PathVariable final long boardId,
            @Validated @RequestBody final ArticleForm form) {
        return createViewModel(this.service.createArticle(createDto(boardId, form)));
    }

    /**
     * Update existing single board article.
     *
     * @param boardId board ID
     * @param form request form
     * @return updated single board article
     */
    @PutMapping("/{articleId}")
    @ResponseStatus(HttpStatus.OK)
    @PostAuthorize("(returnObject.createdBy == authentication.principal.username) or hasRole('ROLE_ADMIN')")
    public ArticleViewModel updateArticle(@PathVariable final long boardId,
            @PathVariable final long articleId,
            @Validated @RequestBody final ArticleForm form) {
        final ArticleDto dto = createDto(boardId, form);
        dto.setId(articleId);
        return createViewModel(this.service.updateArticle(dto));
    }

    /**
     * Create single board article.
     *
     * @param articleId article ID
     */
    @DeleteMapping("/{articleId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteArticle(@PathVariable final long articleId) {
        this.service.deleteArticle(articleId);
    }

    /**
     * Create view model.
     *
     * @param dto target DTO
     * @return generated view model
     */
    private ArticleViewModel createViewModel(final ArticleDto dto) {
        return this.mapper.map(dto, ArticleViewModel.class);
    }

    /**
     * Create DTO.
     *
     * @param boardId board ID
     * @param form form for create article
     * @return generated DTO
     */
    private ArticleDto createDto(final long boardId, final ArticleForm form) {
        if (form.getCategoryId() == 0) {
            throw new BoardException(BoardExceptionMessage.E003.getValue());
        }
        final ArticleDto dto = this.mapper.map(form, ArticleDto.class);
        final BoardDto board = new BoardDto();
        board.setId(boardId);
        dto.setBoard(board);
        final CategoryDto category = new CategoryDto();
        category.setId(form.getCategoryId());
        dto.setCategory(category);
        return dto;
    }
}
