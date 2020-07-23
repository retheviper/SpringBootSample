package com.retheviper.springbootsample.domain.repository.board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.retheviper.springbootsample.domain.entity.board.Article;

/**
 * Article repository.
 *
 * @author retheviper
 */
public interface ArticleRepository extends PagingAndSortingRepository<Article, Long> {

    /**
     * Search articles with board ID.
     *
     * @param pageable pageable
     * @param boardId board ID
     * @return result of search
     */
    Page<Article> findByBoardIdIs(Pageable pageable, long boardId);

    /**
     * Search articles with board ID and containing specific author.
     *
     * @param pageable pageable
     * @param createdby containing author
     * @param boardId board ID
     * @return result of search
     */
    Page<Article> findByCreatedByContainingAndBoardIdIs(Pageable pageable, String createdby, long boardId);

    /**
     * Search articles with board ID and containing specific title.
     *
     * @param pageable pageable
     * @param title containing title
     * @param boardId board ID
     * @return result of search
     */
    Page<Article> findByTitleContainingAndBoardIdIs(Pageable pageable, String title, long boardId);

    /**
     * Search articles with board ID and containing specific content.
     *
     * @param pageable pageable
     * @param content containing content
     * @param boardId board ID
     * @return result of search
     */
    Page<Article> findByContentContainingAndBoardIdIs(Pageable pageable, String content, long boardId);
    /**
     * Search articles with board ID and category Id.
     *
     * @param pageable pageable
     * @param categoryId category ID
     * @param boardId board ID
     * @return result of search
     */
    Page<Article> findByCategoryIdIsAndBoardIdIs(Pageable pageable, long categoryId, long boardId);
}
