package com.retheviper.springbootsample.api.v1.test.controller.member;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;

import com.retheviper.springbootsample.api.v1.controller.member.MemberApiController;
import com.retheviper.springbootsample.api.v1.form.member.MemberForm;
import com.retheviper.springbootsample.api.v1.viewmodel.member.MemberViewModel;
import com.retheviper.springbootsample.common.constant.MemberRole;
import com.retheviper.springbootsample.common.exception.MemberException;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class MemberApiControllerTest {

    private static long MEMBER_ID;

    private static final String TEST_USER_ID = "TEST_USER_ID";

    private static final String TEST_USER_NAME = "TEST_USER_NAME";

    private static final String TEST_USER_NAME_2 = "TEST_USER_NAME_2";

    private static final String TEST_USER_PASSWORD = "TEST_USER_PASSWORD";

    private static final String TEST_USER_PASSWORD_2 = "TEST_USER_PASSWORD_2";

    @Autowired
    private MemberApiController controller;

    @Test
    @Order(3)
    @WithMockUser(username = TEST_USER_ID, roles = MemberRole.USER)
    void listMemberTest() {
        final Optional<MemberViewModel> response = this.controller.listMember().stream()
                .filter(v -> v.getUserId().equals(TEST_USER_ID)).findAny();
        assertAll(() -> {
            final MemberViewModel view = assertDoesNotThrow(() -> response.get());
            assertEquals(TEST_USER_ID, view.getUserId());
            assertEquals(TEST_USER_NAME, view.getName());
            assertTrue(view.getRoles().contains(MemberRole.USER));
        });
    }

    @Test
    @Order(2)
    @WithMockUser(username = TEST_USER_ID, roles = MemberRole.USER)
    void getMemberTest() {
        final MemberViewModel response = this.controller.getMember(MEMBER_ID);
        assertAll(() -> {
            assertNotNull(response);
            assertEquals(TEST_USER_ID, response.getUserId());
            assertEquals(TEST_USER_NAME, response.getName());
            assertEquals(MemberRole.USER, response.getRoles().get(0));
        });
    }

    @Test
    @Order(1)
    @WithAnonymousUser
    void createMemberTest() {
        final MemberForm form = new MemberForm();
        form.setUserId(TEST_USER_ID);
        form.setName(TEST_USER_NAME);
        form.setPassword(TEST_USER_PASSWORD);
        final MemberViewModel response = this.controller.createMember(form);
        assertAll(() -> {
            assertNotNull(response);
            assertEquals(TEST_USER_ID, response.getUserId());
            assertEquals(TEST_USER_NAME, response.getName());
            assertEquals(MemberRole.USER, response.getRoles().get(0));
        });
        MEMBER_ID = response.getId();
    }

    @Test
    @Order(4)
    @WithMockUser(username = TEST_USER_ID, roles = MemberRole.USER)
    void updateMemberTest() {
        final MemberForm form = new MemberForm();
        form.setUserId(TEST_USER_ID);
        form.setName(TEST_USER_NAME_2);
        form.setPassword(TEST_USER_PASSWORD);
        form.setNewPassword(TEST_USER_PASSWORD_2);
        final MemberViewModel response = this.controller.updateMember(MEMBER_ID, form);
        assertAll(() -> {
            assertNotNull(response);
            assertEquals(TEST_USER_NAME_2, response.getName());
            assertEquals(MemberRole.USER, response.getRoles().get(0));
        });
    }

    @Test
    @Order(5)
    @WithMockUser(username = TEST_USER_ID, roles = MemberRole.USER)
    void deleteMemberTest() {
        assertAll(() -> {
            assertDoesNotThrow(() -> this.controller.deleteMember(MEMBER_ID, TEST_USER_PASSWORD_2));
            assertThrows(MemberException.class, () -> this.controller.getMember(MEMBER_ID));
        });
    }
}