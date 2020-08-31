package com.retheviper.springbootsample.api.v1.test.controller.board;

import com.retheviper.springbootsample.api.v1.controller.board.BoardApiController;
import com.retheviper.springbootsample.api.v1.form.board.BoardForm;
import com.retheviper.springbootsample.api.v1.viewmodel.board.BoardViewModel;
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
public class BordApiControllerTest {

    private static long BOARD_ID;

    private static final String TEST_USER_ID = "TEST_USER_ID";

    private static final String TEST_BOARD_NAME = "TEST_BOARD_NAME";

    private static final String TEST_DESCRIPTION = "TEST_DESCRIPTION";

    private static final String TEST_BORD_NAME_2 = "TEST_BOARD_NAME_2";

    @Autowired
    private BoardApiController controller;

    @Test
    @Order(3)
    @WithMockUser(username = TEST_USER_ID, roles = MemberRole.USER)
    void listBoardTest() {
        final Optional<BoardViewModel> response = this.controller.listBoard().stream()
                .filter(b -> b.getId() == BOARD_ID)
                .findAny();
        assertAll(() -> {
            final BoardViewModel view = assertDoesNotThrow(() -> response.get());
            assertEquals(TEST_BOARD_NAME, view.getName());
        });
    }

    @Test
    @Order(2)
    @WithMockUser(username = TEST_USER_ID, roles = MemberRole.USER)
    void getBoardTest() {
        final BoardViewModel response = this.controller.getBoard(BOARD_ID);
        assertAll(() -> {
            assertNotNull(response);
            assertEquals(TEST_BOARD_NAME, response.getName());
        });
    }

    @Test
    @Order(1)
    @WithMockUser(username = TEST_USER_ID, roles = MemberRole.ADMIN)
    void createBoardTest() {
        final BoardForm form = new BoardForm();
        form.setName(TEST_BOARD_NAME);
        form.setDescription(TEST_DESCRIPTION);
        final BoardViewModel response = this.controller.createBoard(form);
        assertAll(() -> {
            assertNotNull(response);
            assertEquals(TEST_BOARD_NAME, response.getName());
            assertEquals(TEST_DESCRIPTION, response.getDescription());
            assertThrows(BoardException.class, () -> this.controller.createBoard(form));
        });
        BOARD_ID = response.getId();
    }

    @Test
    @Order(4)
    @WithMockUser(username = TEST_USER_ID, roles = MemberRole.ADMIN)
    void updateBoardTest() {
        final BoardForm form = new BoardForm();
        form.setName(TEST_BORD_NAME_2);
        final BoardViewModel response = this.controller.updateBoard(BOARD_ID, form);
        assertAll(() -> {
            assertNotNull(response);
            assertEquals(TEST_BORD_NAME_2, response.getName());
        });
    }

    @Test
    @Order(5)
    @WithMockUser(username = TEST_USER_ID, roles = MemberRole.ADMIN)
    void deleteBoardTest() {
        assertAll(() -> {
            assertDoesNotThrow(() -> this.controller.deleteBoard(BOARD_ID));
            assertThrows(BoardException.class, () -> this.controller.getBoard(BOARD_ID));
        });
    }
}