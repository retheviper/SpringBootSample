package com.retheviper.springbootsample.api.v1.test.controller.board;

import com.retheviper.springbootsample.api.v1.controller.board.ArticleApiController;
import com.retheviper.springbootsample.api.v1.form.board.ArticleForm;
import com.retheviper.springbootsample.api.v1.test.controller.testbase.BoardTestBase;
import com.retheviper.springbootsample.api.v1.viewmodel.board.ArticleViewModel;
import com.retheviper.springbootsample.common.constant.MemberRole;
import com.retheviper.springbootsample.common.exception.BoardException;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.EntityModel;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class ArticleApiControllerTest {

    @Autowired
    private BoardTestBase base;

    private static long BOARD_ID;

    private static long CATEGORY_ID;

    private static long ARTICLE_ID;

    private static final String TEST_USER_ID = "TEST_USER_ID";

    private static final String TEST_TITLE = "TEST_TITLE";

    private static final String TEST_TITLE_2 = "TEST_TITLE_2";

    private static final String TEST_CONTENT = "TEST_CONTENT";

    private static final String TEST_CONTENT_2 = "TEST_CONTENT_2";

    @Autowired
    private ArticleApiController controller;

    @Test
    @Order(1)
    @WithMockUser(username = TEST_USER_ID, roles = MemberRole.ADMIN)
    void initTest() {
        BOARD_ID = assertDoesNotThrow(this.base::initBoard);
        CATEGORY_ID = assertDoesNotThrow(() -> this.base.initCategory(BOARD_ID));
    }

    @Test
    @Order(4)
    @WithMockUser(username = TEST_USER_ID, roles = MemberRole.USER)
    void listArticleTest() {
        final Optional<ArticleViewModel> response = StreamSupport.stream(this.controller.listArticle(1, 0, 20).spliterator(), false)
                .map(EntityModel<ArticleViewModel>::getContent).findAny();
        assertAll(() -> {
            final ArticleViewModel view = assertDoesNotThrow(() -> response.get());
            assertEquals(TEST_TITLE, view.getTitle());
            assertEquals(TEST_CONTENT, view.getContent());
            assertEquals(TEST_USER_ID, view.getCreatedBy());
        });
    }

    @Test
    @Order(3)
    @WithMockUser(username = TEST_USER_ID, roles = MemberRole.USER)
    void getArticleTest() {
        final ArticleViewModel response = this.controller.getArticle(ARTICLE_ID);
        assertAll(() -> {
            assertNotNull(response);
            assertEquals(TEST_TITLE, response.getTitle());
            assertEquals(TEST_CONTENT, response.getContent());
            assertEquals(TEST_USER_ID, response.getCreatedBy());
        });
    }

    @Test
    @Order(2)
    @WithMockUser(username = TEST_USER_ID, roles = MemberRole.USER)
    void createArticleTest() {
        final ArticleForm form = new ArticleForm();
        form.setTitle(TEST_TITLE);
        form.setContent(TEST_CONTENT);
        form.setCategoryId(CATEGORY_ID);
        final ArticleViewModel response = this.controller.createArticle(BOARD_ID, form);
        assertAll(() -> {
            assertNotNull(response);
            assertEquals(TEST_TITLE, response.getTitle());
            assertEquals(TEST_CONTENT, response.getContent());
            assertEquals(TEST_USER_ID, response.getCreatedBy());
        });
        ARTICLE_ID = response.getId();
    }

    @Test
    @Order(5)
    @WithMockUser(username = TEST_USER_ID, roles = MemberRole.USER)
    void updateArticleTest() {
        final ArticleForm form = new ArticleForm();
        form.setTitle(TEST_TITLE_2);
        form.setContent(TEST_CONTENT_2);
        form.setCategoryId(CATEGORY_ID);
        final ArticleViewModel response = this.controller.updateArticle(BOARD_ID, ARTICLE_ID, form);
        assertAll(() -> {
            assertNotNull(response);
            assertEquals(TEST_TITLE_2, response.getTitle());
            assertEquals(TEST_CONTENT_2, response.getContent());
            assertEquals(TEST_USER_ID, response.getCreatedBy());
        });
    }

    @Test
    @Order(6)
    @WithMockUser(username = TEST_USER_ID, roles = MemberRole.USER)
    void deleteArticleTest() {
        assertAll(() -> {
            assertDoesNotThrow(() -> this.controller.deleteArticle(ARTICLE_ID));
            assertThrows(BoardException.class, () -> this.controller.getArticle(ARTICLE_ID));
        });
    }

    @Test
    @Order(7)
    @WithMockUser(username = TEST_USER_ID, roles = MemberRole.ADMIN)
    void finalizeTest() {
        assertDoesNotThrow(() -> this.base.finalizeCategory(CATEGORY_ID));
        assertDoesNotThrow(() -> this.base.finalizeBoard(BOARD_ID));
    }
}