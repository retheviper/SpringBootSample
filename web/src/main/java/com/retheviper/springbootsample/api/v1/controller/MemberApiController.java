package com.retheviper.springbootsample.api.v1.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.retheviper.springbootsample.api.v1.form.CreateMemberForm;
import com.retheviper.springbootsample.api.v1.form.UpdateMemberForm;
import com.retheviper.springbootsample.api.v1.viewmodel.MemberViewModel;
import com.retheviper.springbootsample.application.dto.MemberDto;
import com.retheviper.springbootsample.application.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Member API controller class.
 *
 * @author retheviper
 */
@Slf4j
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/web/member")
public class MemberApiController {

    /**
     * Data model converter
     */
    private final ModelMapper mapper;

    /**
     * Member service class
     */
    private final MemberService service;

    /**
     * Get list of members.
     *
     * @return list of view model
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MemberViewModel> listMember() {
        log.info("Request: List Member Accepted.");
        return this.service.listMember().stream().map(dto -> this.mapper.map(dto, MemberViewModel.class))
                .collect(Collectors.toList());
    }

    /**
     * Get single member by member ID.
     *
     * @param uid member ID
     * @return view model
     */
    @GetMapping("/{uid}")
    @ResponseStatus(HttpStatus.OK)
    public MemberViewModel getMember(@NotBlank @PathVariable final String uid) {
        log("Get", uid);
        return this.mapper.map(this.service.getMember(uid), MemberViewModel.class);
    }

    /**
     * Create new member.
     *
     * @param form member create form
     * @return view model
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MemberViewModel createMember(@Validated @RequestBody final CreateMemberForm form) {
        log("Create", form.getUid());
        return this.mapper.map(this.service.createMember(this.mapper.map(form, MemberDto.class)),
                MemberViewModel.class);
    }

    /**
     * Update existing member.
     *
     * @param form member update form
     * @param uid member ID
     * @return view model
     */
    @PutMapping("/{uid}")
    @ResponseStatus(HttpStatus.OK)
    public MemberViewModel updateMember(@Validated @RequestBody final UpdateMemberForm form,
            @PathVariable final String uid) {
        log("Update", uid);
        final MemberDto dto = this.mapper.map(form, MemberDto.class);
        dto.setUid(uid);
        return this.mapper.map(this.service.updateMember(dto), MemberViewModel.class);
    }

    /**
     * Delete existing member.
     *
     * @param uid member ID
     * @param password member password
     */
    @DeleteMapping("/{uid}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteMember(@NotBlank @PathVariable final String uid, @NotBlank @RequestBody final String password) {
        log("Delete", uid);
        final MemberDto dto = MemberDto.builder().uid(uid).password(password).build();
        this.service.deleteMember(dto);
    }

    /**
     * Write log for action.
     *
     * @param request type of action
     * @param uid member ID
     */
    private void log(final String request, final String uid) {
        log.info("Request: {} Member Accepted. (Member ID: {})", request, uid);
    }
}
