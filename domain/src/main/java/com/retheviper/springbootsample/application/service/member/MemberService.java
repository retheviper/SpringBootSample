package com.retheviper.springbootsample.application.service.member;

import com.retheviper.springbootsample.application.dto.member.MemberDto;

import java.util.List;

/**
 * Member service class.
 *
 * @author retheviper
 */
public interface MemberService {

    /**
     * Get list of members.
     *
     * @return List of member DTO
     */
    List<MemberDto> listMember();

    /**
     * Get single member.
     *
     * @param dto Member DTO
     * @return Member DTO
     */
    MemberDto getMember(MemberDto dto);

    /**
     * Create new member.
     *
     * @param dto Member DTO
     * @return Member DTO
     */
    MemberDto createMember(MemberDto dto);

    /**
     * Update existing member.
     *
     * @param dto Member DTO
     * @return Member DTO
     */
    MemberDto updateMember(MemberDto dto);

    /**
     * Delete existing member.
     *
     * @param dto Member DTO
     */
    void deleteMember(MemberDto dto);
}
