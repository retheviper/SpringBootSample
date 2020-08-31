package com.retheviper.springbootsample.application.service.board;

import com.retheviper.springbootsample.application.dto.board.ArticleDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Board article service class.
 *
 * @author retheviper
 */
public interface ArticleService {

    /**
     * Get list of board articles.
     *
     * @param pageable pageable
     * @return List of board article DTO
     * @oaran boardId Board ID of article belongs
     */
    Page<ArticleDto> listArticle(long boardId, Pageable pageable);

    /**
     * Get list of board articles with keyword.
     * <p>
     * Search keword has several types as createdBy(author), title, content, category.
     *
     * @param boardId     Board ID of article belongs
     * @param pageable    pageable
     * @param keyword     keyword
     * @param keywordType type of keyword
     * @return List of board article DTO
     */
    Page<ArticleDto> listArticle(long boardId, Pageable pageable, String keyword, int keywordType);

    /**
     * Get single board article by board article's ID.
     *
     * @param id Board article's ID
     * @return Board article DTO
     */
    ArticleDto getArticle(long id);

    /**
     * Create new board article.
     *
     * @param dto Board article DTO
     * @return Board article DTO
     */
    ArticleDto createArticle(ArticleDto dto);

    /**
     * Update existing board article.
     *
     * @param dto Board article DTO
     * @return Board article DTO
     */
    ArticleDto updateArticle(ArticleDto dto);

    /**
     * Delete existing board article.
     *
     * @param id Board article's ID
     */
    void deleteArticle(long id);

    /**
     * Check existing by ID.
     *
     * @param id ID
     * @return result of check
     */
    void checkExists(long id);
}
