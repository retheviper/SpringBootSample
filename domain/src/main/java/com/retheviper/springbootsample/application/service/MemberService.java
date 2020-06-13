package com.retheviper.springbootsample.application.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.retheviper.springbootsample.application.dto.MemberDto;

/**
 * Member service class.
 *
 * @author retheviper
 */
public interface MemberService extends UserDetailsService {

    /**
     * Get list of members.
     *
     * @return List of member DTO
     */
    List<MemberDto> listMember();

    /**
     * Get single member by member ID.
     *
     * @param uid member ID
     * @return Member DTO
     */
    MemberDto getMember(String uid);


    /**
     * Get single member by DTO.
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
     * @return Member DTO
     */
    void deleteMember(MemberDto dto);
}
