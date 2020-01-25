package com.retheviper.springbootsample.web.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.retheviper.springbootsample.application.dto.MemberDto;
import com.retheviper.springbootsample.application.service.MemberService;
import com.retheviper.springbootsample.web.form.CreateMemberForm;
import com.retheviper.springbootsample.web.form.UpdateMemberForm;
import com.retheviper.springbootsample.web.viewmodel.MemberViewModel;

@CrossOrigin
@RestController
@RequestMapping("api/v1/web/member")
public class MemberApiController {

	private final ModelMapper mapper;

	private final MemberService service;

	@Autowired
	public MemberApiController(final ModelMapper mapper, final MemberService service) {
		this.mapper = mapper;
		this.service = service;
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<MemberViewModel> listMember() {
		return this.service.listMember().stream().map(dto -> this.mapper.map(dto, MemberViewModel.class))
				.collect(Collectors.toList());
	}

	@GetMapping("/{memberId}")
	@ResponseStatus(HttpStatus.OK)
	public MemberViewModel getMember(@PathVariable final String memberId) {
		return this.mapper.map(this.service.getMember(memberId), MemberViewModel.class);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public MemberViewModel createMember(@Validated final CreateMemberForm form) {
		return this.mapper.map(this.service.createMember(this.mapper.map(form, MemberDto.class)),
				MemberViewModel.class);
	}

	@PutMapping("/{memberId}")
	@ResponseStatus(HttpStatus.OK)
	public MemberViewModel updateMember(@Validated final UpdateMemberForm form, @PathVariable final String memberId) {
		final MemberDto dto = this.mapper.map(form, MemberDto.class);
		dto.setMemberId(memberId);
		return this.mapper.map(this.service.updateMember(dto), MemberViewModel.class);
	}

	@DeleteMapping("/{memberId}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteMember(@PathVariable final String memberId, final String password) {
		final MemberDto dto = MemberDto.builder().memberId(memberId).password(password).build();
		this.service.deleteMember(dto);
	}
}
