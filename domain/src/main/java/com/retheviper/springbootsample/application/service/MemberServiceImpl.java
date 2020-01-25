package com.retheviper.springbootsample.application.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.retheviper.springbootsample.application.constant.ExceptionMessageConstant;
import com.retheviper.springbootsample.application.dto.MemberDto;
import com.retheviper.springbootsample.application.exception.MemberException;
import com.retheviper.springbootsample.domain.entity.Member;
import com.retheviper.springbootsample.domain.repository.MemberRepository;


@Service
public class MemberServiceImpl implements MemberService {

	/** Converter */
	private final ModelMapper mapper;

	/** Password Encryption */
	private final PasswordEncoder encoder;

	/** JPA Repository */
	private final MemberRepository repository;

	@Autowired
	public MemberServiceImpl(final ModelMapper mapper, final PasswordEncoder encoder,
			final MemberRepository repository) {
		this.mapper = mapper;
		this.encoder = encoder;
		this.repository = repository;
	}

	@Override
	@Transactional(readOnly = true)
	public List<MemberDto> listMember() {
		return this.repository.findAll().stream().map(entity -> createDto(entity)).collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public MemberDto getMember(final String memberId) {
		final Optional<Member> result = this.repository.findByMemberId(memberId);
		return createDto(
				result.orElseThrow(() -> new NullPointerException(ExceptionMessageConstant.COMMON_E000.getMessage())));
	}

	@Override
	@Transactional
	public MemberDto createMember(final MemberDto dto) {
		if (this.repository.existsByMemberId(dto.getMemberId())) {
			throw new MemberException(ExceptionMessageConstant.MEMBER_E000.getMessage());
		}
		dto.setJoinedDate(LocalDateTime.now());
		return saveMemberWithEncrpytion(dto);
	}

	@Override
	@Transactional
	public MemberDto updateMember(final MemberDto dto) {
		if (checkPasswordMatches(dto)) {
			throw new MemberException(ExceptionMessageConstant.COMMON_E002.getMessage());
		}
		return saveMemberWithEncrpytion(dto);
	}

	@Override
	@Transactional
	public void deleteMember(final MemberDto dto) {
		if (!checkPasswordMatches(dto)) {
			throw new MemberException(ExceptionMessageConstant.COMMON_E001.getMessage());
		}
		this.repository.deleteByMemberId(dto.getMemberId());
	}

	private MemberDto saveMemberWithEncrpytion(final MemberDto dto) {
		final String encodedPassword = this.encoder.encode(dto.getPassword());
		dto.setPassword(encodedPassword);
		return createDto(this.repository.save(this.mapper.map(dto, Member.class)));
	}

	private boolean checkPasswordMatches(final MemberDto dto) {
		final String inputed = Optional.ofNullable(dto).map(MemberDto::getPassword)
				.orElseThrow(() -> new MemberException(ExceptionMessageConstant.COMMON_E003.getMessage()));
		final String existing = this.repository.findByMemberId(dto.getMemberId()).map(Member::getPassword)
				.orElseThrow(() -> new MemberException(ExceptionMessageConstant.MEMBER_E001.getMessage()));
		return this.encoder.matches(inputed, existing);
	}

	private MemberDto createDto(final Member entity) {
		return this.mapper.map(entity, MemberDto.class);
	}
}
