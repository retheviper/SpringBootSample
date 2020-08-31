package com.retheviper.springbootsample.api.v1.test.controller.board;

import com.retheviper.springbootsample.api.v1.controller.board.CategoryApiController;
import com.retheviper.springbootsample.api.v1.form.board.CategoryForm;
import com.retheviper.springbootsample.api.v1.test.controller.testbase.BoardTestBase;
import com.retheviper.springbootsample.api.v1.viewmodel.board.CategoryViewModel;
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

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class CategoryApiControllerTest {

    @Autowired
    private BoardTestBase base;

    private static long BOARD_ID;

    private static long CATEGORY_ID;

    private static final String TEST_USER_ID = "TEST_USER_ID";

    private static final String TEST_NAME = "TEST_CATEGORY_NAME";

    private static final String TEST_NAME_2 = "TEST_CATEGORY_NAME_2";

    @Autowired
    private CategoryApiController controller;

    @Test
    @Order(1)
    @WithMockUser(username = TEST_USER_ID, roles = MemberRole.ADMIN)
    void initTest() {
        BOARD_ID = assertDoesNotThrow(this.base::initBoard);
    }

    @Test
    @Order(4)
    @WithMockUser(username = TEST_USER_ID, roles = MemberRole.USER)
    void listCategoryTest() {
        final Optional<CategoryViewModel> response = this.controller.listCategory(BOARD_ID).stream().findAny();
        assertAll(() -> {
            final CategoryViewModel view = assertDoesNotThrow(() -> response.get());
            assertEquals(CATEGORY_ID, view.getId());
            assertEquals(TEST_NAME, view.getName());
        });
    }

    @Test
    @Order(3)
    @WithMockUser(username = TEST_USER_ID, roles = MemberRole.USER)
    void getCategoryTest() {
        final CategoryViewModel response = this.controller.getCategory(CATEGORY_ID);
        assertAll(() -> {
            assertNotNull(response);
            assertEquals(CATEGORY_ID, response.getId());
            assertEquals(TEST_NAME, response.getName());
        });
    }

    @Test
    @Order(2)
    @WithMockUser(username = TEST_USER_ID, roles = MemberRole.ADMIN)
    void createCategoryTest() {
        final CategoryForm form = new CategoryForm();
        form.setName(TEST_NAME);
        final CategoryViewModel response = this.controller.createCategory(BOARD_ID, form);
        assertAll(() -> {
            assertNotNull(response);
            assertEquals(TEST_NAME, response.getName());
        });
        CATEGORY_ID = response.getId();
    }

    @Test
    @Order(5)
    @WithMockUser(username = TEST_USER_ID, roles = MemberRole.ADMIN)
    void updateCategoryTest() {
        final CategoryForm form = new CategoryForm();
        form.setName(TEST_NAME_2);
        final CategoryViewModel response = this.controller.updateCategory(BOARD_ID, CATEGORY_ID, form);
        assertAll(() -> {
            assertNotNull(response);
            assertEquals(TEST_NAME_2, response.getName());
        });
    }

    @Test
    @Order(6)
    @WithMockUser(username = TEST_USER_ID, roles = MemberRole.ADMIN)
    void deleteCategoryTest() {
        assertAll(() -> {
            assertDoesNotThrow(() -> this.controller.deleteCategory(CATEGORY_ID));
            assertThrows(BoardException.class, () -> this.controller.getCategory(CATEGORY_ID));
        });
    }

    @Test
    @Order(7)
    @WithMockUser(username = TEST_USER_ID, roles = MemberRole.ADMIN)
    void finalizeTest() {
        assertDoesNotThrow(() -> this.base.finalizeBoard(BOARD_ID));
    }
}
