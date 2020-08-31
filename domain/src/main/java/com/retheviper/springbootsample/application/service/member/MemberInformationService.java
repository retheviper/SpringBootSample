package com.retheviper.springbootsample.application.service.member;

import com.retheviper.springbootsample.application.dto.member.MemberInformationDto;

import java.util.List;

/**
 * Member information service class.
 *
 * @author retheviper
 */
public interface MemberInformationService {

    /**
     * Get list of member informations.
     *
     * @return List of member information DTO
     */
    List<MemberInformationDto> listMemberInformation();

    /**
     * Get single member by member ID.
     *
     * @param dto Member information DTO
     * @return Member information DTO
     */
    MemberInformationDto getMemberInformation(MemberInformationDto dto);

    /**
     * Create new member.
     *
     * @param dto Member information DTO
     * @return Member information DTO
     */
    MemberInformationDto createMemberInformation(MemberInformationDto dto);

    /**
     * Update existing member.
     *
     * @param dto Member information DTO
     * @return Member information DTO
     */
    MemberInformationDto updateMemberInformation(MemberInformationDto dto);

    /**
     * Delete existing member.
     *
     * @param dto Member information DTO
     */
    void deleteMemberInformation(MemberInformationDto dto);
}
