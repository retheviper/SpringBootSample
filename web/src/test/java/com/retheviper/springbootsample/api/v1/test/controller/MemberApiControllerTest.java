package com.retheviper.springbootsample.api.v1.test.controller;

import com.retheviper.springbootsample.api.v1.controller.MemberApiController;
import com.retheviper.springbootsample.api.v1.form.CreateMemberForm;
import com.retheviper.springbootsample.domain.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
public class MemberApiControllerTest {

    @Autowired
    private MemberApiController controller;

    @Autowired
    private MemberRepository repository;

    @Test
    void createMemberTest() {
        final CreateMemberForm form = new CreateMemberForm();
        form.setUid("id");
        form.setName("name");
        form.setPassword("password");
        assertAll(() -> System.out.println(this.controller.createMember(form)));

        System.out.println(repository.findAll());
    }

    @Test
    void listMemberTest() {
        assertAll(() -> System.out.println(this.controller.listMember()));
    }

}
