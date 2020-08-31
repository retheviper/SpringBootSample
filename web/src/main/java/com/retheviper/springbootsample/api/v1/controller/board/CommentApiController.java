package com.retheviper.springbootsample.api.v1.controller.board;

import com.retheviper.springbootsample.api.v1.form.board.CommentForm;
import com.retheviper.springbootsample.api.v1.viewmodel.board.CommentViewModel;
import com.retheviper.springbootsample.application.dto.board.ArticleDto;
import com.retheviper.springbootsample.application.dto.board.CommentDto;
import com.retheviper.springbootsample.application.service.board.CommentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Article comment controller class.
 *
 * @author retheviper
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/web/boards/{boardId}/articles/{articleId}/comments")
public class CommentApiController {

    /**
     * Data model converter
     */
    private final ModelMapper mapper;

    /**
     * Board article's comment service
     */
    private final CommentService service;

    /**
     * Get list of comments.
     *
     * @return list of comments
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CommentViewModel> listComment(@PathVariable final long articleId) {
        return this.service.listComment(articleId).stream().map(this::createViewModel)
                .collect(Collectors.toList());
    }

    /**
     * Get single comment.
     *
     * @param commentId comment ID
     * @return requested single comment
     */
    @GetMapping("/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public CommentViewModel getComment(@PathVariable final long commentId) {
        return createViewModel(this.service.getComment(commentId));
    }

    /**
     * Create single comment.
     *
     * @param form request form
     * @return created single comment
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentViewModel createComment(@PathVariable final long articleId,
                                          @Validated @RequestBody final CommentForm form) {
        final CommentDto dto = this.mapper.map(form, CommentDto.class);
        final ArticleDto article = new ArticleDto();
        article.setId(articleId);
        dto.setArticle(article);
        return createViewModel(this.service.createComment(dto));
    }

    /**
     * Update single comment.
     *
     * @param commentId comment ID
     * @param form      request form
     * @return updated single comment
     */
    @PutMapping("/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    @PostAuthorize("returnObject.createdBy == authentication.principal.username")
    public CommentViewModel updateComment(@PathVariable final long articleId, @PathVariable final long commentId,
                                          @Validated @RequestBody final CommentForm form) {
        final CommentDto dto = this.mapper.map(form, CommentDto.class);
        dto.setId(commentId);
        final ArticleDto article = new ArticleDto();
        article.setId(articleId);
        dto.setArticle(article);
        return createViewModel(this.service.updateComment(dto));
    }

    /**
     * Delete existing single comment.
     *
     * @param commentId comment ID
     */
    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteComment(@PathVariable final long commentId) {
        this.service.deleteComment(commentId);
    }

    /**
     * Create view model.
     *
     * @param dto target DTO
     * @return generated view model
     */
    private CommentViewModel createViewModel(final CommentDto dto) {
        return this.mapper.map(dto, CommentViewModel.class);
    }
}
