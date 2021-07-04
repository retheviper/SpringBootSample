package com.retheviper.springbootsample.api.v1.test.controller.board;

import com.retheviper.springbootsample.api.v1.controller.board.CommentApiController;
import com.retheviper.springbootsample.api.v1.form.board.CommentForm;
import com.retheviper.springbootsample.api.v1.test.controller.testbase.BoardTestBase;
import com.retheviper.springbootsample.api.v1.viewmodel.board.CommentViewModel;
import com.retheviper.springbootsample.common.constant.MemberRole;
import com.retheviper.springbootsample.common.exception.BoardException;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.Optional;

import static com.retheviper.springbootsample.api.v1.test.controller.testbase.TestConstants.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class CommentApiControllerTest {

    @Autowired
    private BoardTestBase base;

    private static long BOARD_ID;

    private static long CATEGORY_ID;

    private static long ARTICLE_ID;

    private static long COMMENT_ID;

    @Autowired
    private CommentApiController controller;

    @Test
    @Order(1)
    @WithMockUser(username = TEST_USER_ID, roles = MemberRole.ADMIN)
    void initTest() {
        BOARD_ID = assertDoesNotThrow(this.base::initBoard);
        CATEGORY_ID = assertDoesNotThrow(() -> this.base.initCategory(BOARD_ID));
        ARTICLE_ID = assertDoesNotThrow(() -> this.base.initArticle(BOARD_ID, CATEGORY_ID));
    }

    @Test
    @Order(4)
    @WithMockUser(username = TEST_USER_ID, roles = MemberRole.USER)
    void listCommentTest() {
        final Optional<CommentViewModel> response = this.controller.listComment(ARTICLE_ID).stream().findAny();
        assertAll(() -> {
            final CommentViewModel view = assertDoesNotThrow(response::get);
            assertEquals(TEST_CONTENT, view.getContent());
            assertEquals(TEST_USER_ID, view.getCreatedBy());
        });
    }

    @Test
    @Order(3)
    @WithMockUser(username = TEST_USER_ID, roles = MemberRole.USER)
    void getCommentTest() {
        final CommentViewModel response = this.controller.getComment(COMMENT_ID);
        assertAll(() -> {
            assertNotNull(response);
            assertEquals(TEST_CONTENT, response.getContent());
            assertEquals(TEST_USER_ID, response.getCreatedBy());
        });
    }

    @Test
    @Order(2)
    @WithMockUser(username = TEST_USER_ID, roles = MemberRole.USER)
    void createCommentTest() {
        final CommentForm form = new CommentForm();
        form.setContent(TEST_CONTENT);
        final CommentViewModel response = this.controller.createComment(ARTICLE_ID, form);
        assertAll(() -> {
            assertNotNull(response);
            assertEquals(TEST_CONTENT, response.getContent());
            assertEquals(TEST_USER_ID, response.getCreatedBy());
        });
        COMMENT_ID = response.getId();
    }

    @Test
    @Order(5)
    @WithMockUser(username = TEST_USER_ID, roles = MemberRole.USER)
    void updateCommentTest() {
        final CommentForm form = new CommentForm();
        form.setContent(TEST_CONTENT_2);
        final CommentViewModel response = this.controller.updateComment(ARTICLE_ID, COMMENT_ID, form);
        assertAll(() -> {
            assertNotNull(response);
            assertEquals(TEST_CONTENT_2, response.getContent());
            assertEquals(TEST_USER_ID, response.getCreatedBy());
        });
    }

    @Test
    @Order(6)
    @WithMockUser(username = TEST_USER_ID, roles = MemberRole.USER)
    void deleteCommentTest() {
        assertAll(() -> {
            assertDoesNotThrow(() -> this.controller.deleteComment(COMMENT_ID));
            assertThrows(BoardException.class, () -> this.controller.getComment(COMMENT_ID));
        });
    }

    @Test
    @Order(7)
    @WithMockUser(username = TEST_USER_ID, roles = MemberRole.ADMIN)
    void finalizeTest() {
        assertDoesNotThrow(() -> this.base.finalizeArticle(ARTICLE_ID));
        assertDoesNotThrow(() -> this.base.finalizeCategory(CATEGORY_ID));
        assertDoesNotThrow(() -> this.base.finalizeBoard(BOARD_ID));
    }
}
