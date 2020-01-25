package com.retheviper.springbootsample.application.service;

import java.util.List;

import com.retheviper.springbootsample.application.dto.MemberDto;

public interface MemberService {

	/**
	 *
	 * @return
	 */
	List<MemberDto> listMember();

	/**
	 *
	 * @param memberId
	 * @return
	 */
	MemberDto getMember(String memberId);

	/**
	 *
	 * @param dto
	 * @return
	 */
	MemberDto createMember(MemberDto dto);

	/**
	 *
	 * @param dto
	 * @return
	 */
	MemberDto updateMember(MemberDto dto);

	/**
	 *
	 * @param dto
	 */
	void deleteMember(MemberDto dto);
}
