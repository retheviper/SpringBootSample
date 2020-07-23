package com.retheviper.springbootsample.api.v1.controller.board;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
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

import com.retheviper.springbootsample.api.v1.form.board.CommentForm;
import com.retheviper.springbootsample.api.v1.viewmodel.board.CommentViewModel;
import com.retheviper.springbootsample.application.dto.board.ArticleDto;
import com.retheviper.springbootsample.application.dto.board.CommentDto;
import com.retheviper.springbootsample.application.service.board.CommentService;

import lombok.RequiredArgsConstructor;

/**
 * Article comment controller class.
 *
 * @author retheviper
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/web/board/{boardId}/article/{articleId}/comment")
public class CommentApiController {

    /**
     * Data model converter
     */
    private final ModelMapper mapper;

    /**
     * Board article's comment service
     */
    private final CommentService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CommentViewModel> listComment(@PathVariable final long articleId) {
        return this.service.listComment(articleId).stream().map(this::createViewModel)
                .collect(Collectors.toList());
    }

    @GetMapping("/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public CommentViewModel getComment(@PathVariable final long commentId) {
        return createViewModel(this.service.getComment(commentId));
    }

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

    @PutMapping("/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public CommentViewModel updateComment(@PathVariable final long articleId, @PathVariable final long commentId,
            @Validated @RequestBody final CommentForm form) {
        final CommentDto dto = this.mapper.map(form, CommentDto.class);
        dto.setId(commentId);
        final ArticleDto article = new ArticleDto();
        article.setId(articleId);
        dto.setArticle(article);
        return createViewModel(this.service.updateComment(dto));
    }

    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteComment(@PathVariable final long commentId) {
        this.service.deleteComment(commentId);
    }

    /**
     * Create view model.
     *
     * @param dto Target DTO
     * @return Generated view model
     */
    private CommentViewModel createViewModel(final CommentDto dto) {
        return this.mapper.map(dto, CommentViewModel.class);
    }
}
