package com.retheviper.springbootsample.application.service.board;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.retheviper.springbootsample.application.dto.board.ArticleDto;
import com.retheviper.springbootsample.application.dto.board.CategoryDto;
import com.retheviper.springbootsample.common.exception.BoardException;
import com.retheviper.springbootsample.common.util.UserContext;
import com.retheviper.springbootsample.domain.entity.board.Article;
import com.retheviper.springbootsample.domain.repository.board.ArticleRepository;

import lombok.RequiredArgsConstructor;

/**
 * Board article service class. (Implementation)
 *
 * @author retheviper
 */
@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    /**
     * Data model converter
     */
    private final ModelMapper mapper;

    /**
     * Board article repository
     */
    private final ArticleRepository repository;

    /**
     * Board service
     */
    private final BoardService boardService;

    /**
     * Category service
     */
    private final CategoryService categoryService;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ArticleDto> listArticle(final long boardId, final Pageable pageable) {
        return this.repository.findByBoardIdIs(pageable, boardId).map(this::createDto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ArticleDto> listArticle(final long boardId, final Pageable pageable, final String keyword,
            final int keywordType) {
        switch (keywordType) {
        case 0:
            return this.repository.findByTitleContainingAndBoardIdIs(pageable, keyword, boardId)
                    .map(this::createDto);
        case 1:
            return this.repository.findByContentContainingAndBoardIdIs(pageable, keyword, boardId)
                    .map(this::createDto);
        case 2:
            return this.repository.findByCreatedByContainingAndBoardIdIs(pageable, keyword, boardId)
                    .map(this::createDto);
        case 3:
            final CategoryDto dto = new CategoryDto();
            dto.setName(keyword);
            return this.repository.findByCategoryIdIsAndBoardIdIs(pageable,
                    this.categoryService.getCategory(dto).getId(), boardId)
                    .map(this::createDto);
        default:
            throw new BoardException("no such type");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public ArticleDto getArticle(final long id) {
        return createDto(getEntity(id));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public ArticleDto createArticle(final ArticleDto dto) {
        checkBoardAndCategory(dto);
        return save(this.mapper.map(dto, Article.class));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public ArticleDto updateArticle(final ArticleDto dto) {
        checkBoardAndCategory(dto);
        final Article entity = getEntity(dto.getId());
        if (!UserContext.loginedUserMatches(entity.getCreatedBy())) {
            throw new BoardException("author not equals");
        }
        dto.setBoard(this.boardService.getBoard(dto.getBoard()));
        dto.setCategory(this.categoryService.getCategory(dto.getCategory()));
        this.mapper.map(dto, entity);
        return save(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void deleteArticle(final long id) {
        if (!UserContext.loginedUserMatches(getEntity(id).getCreatedBy())) {
            throw new BoardException("author not equals");
        }
        this.repository.deleteById(id);
    }

    /**
     * {@inheritDoc}
     */
    public void checkExists(final long id) {
        if (!this.repository.existsById(id)) {
            throw new BoardException("Article not exists");
        }
    }

    private void checkBoardAndCategory(final ArticleDto dto) {
        this.boardService.checkExists(dto.getBoard().getId());
        this.categoryService.checkExists(dto.getCategory().getId());
    }

    /**
     * Save entity to repository and get result.
     *
     * @param entity enitity to save
     * @return Saved result as DTO
     */
    private ArticleDto save(final Article entity) {
        return this.createDto(this.repository.save(entity));
    }

    /**
     * Map entity to DTO.
     *
     * @param entity Article entity
     * @return Article DTO
     */
    private ArticleDto createDto(final Article entity) {
        return this.mapper.map(entity, ArticleDto.class);
    }

    /**
     * Get entity from repository.
     *
     * @param uid Article's ID
     * @return Article entity
     */
    private Article getEntity(final long id) {
        return this.repository.findById(id).orElseThrow(() -> new BoardException("Article not exists"));
    }
}
