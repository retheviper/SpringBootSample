package com.retheviper.springbootsample.application.service.board;

import com.retheviper.springbootsample.application.dto.board.CommentDto;
import com.retheviper.springbootsample.common.constant.message.BoardExceptionMessage;
import com.retheviper.springbootsample.common.exception.BoardException;
import com.retheviper.springbootsample.domain.entity.board.Comment;
import com.retheviper.springbootsample.domain.repository.board.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Article comment service class. (Implementation)
 *
 * @author retheviper
 */
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    /**
     * Data model converter
     */
    private final ModelMapper mapper;

    /**
     * Comment repository
     */
    private final CommentRepository repository;

    /**
     * Board article service
     */
    private final ArticleService articleService;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<CommentDto> listComment(final long articleId) {
        return this.repository.findAllByArticleId(articleId)
                .stream()
                .map(this::createDto)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public CommentDto getComment(final long id) {
        return createDto(getEntity(id));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public CommentDto createComment(final CommentDto dto) {
        this.articleService.checkExists(dto.getArticle().getId());
        return save(this.mapper.map(dto, Comment.class));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public CommentDto updateComment(final CommentDto dto) {
        final long articleId = dto.getArticle().getId();
        this.articleService.checkExists(articleId);
        final Comment entity = getEntity(dto.getId());
        dto.setArticle(this.articleService.getArticle(articleId));
        this.mapper.map(dto, entity);
        return save(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void deleteComment(final long id) {
        this.repository.deleteById(id);
    }

    /**
     * {@inheritDoc}
     */
    public void checkExists(final long id) {
        if (!this.repository.existsById(id)) {
            throw new BoardException(BoardExceptionMessage.E007.getValue());
        }
    }

    /**
     * Save entity to repository and get result.
     *
     * @param entity enitity to save
     * @return Saved result as DTO
     */
    private CommentDto save(final Comment entity) {
        return this.createDto(this.repository.save(entity));
    }

    /**
     * Map entity to DTO.
     *
     * @param entity Article entity
     * @return Article DTO
     */
    private CommentDto createDto(final Comment entity) {
        return this.mapper.map(entity, CommentDto.class);
    }

    /**
     * Get entity from repository.
     *
     * @param uid Article's ID
     * @return Article entity
     */
    private Comment getEntity(final long id) {
        return this.repository.findById(id).orElseThrow(BoardException::new);
    }

}
