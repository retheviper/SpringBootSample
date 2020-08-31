package com.retheviper.springbootsample.application.dto.member;

import com.retheviper.springbootsample.application.dto.board.BoardDto;
import lombok.Data;

import javax.validation.constraints.Email;
import java.util.List;

/**
 * Member information DTO class.
 *
 * @author retheviper
 */
@Data
public class MemberInformationDto {

    /**
     * Primary Key
     */
    private long id;

    /**
     * Member's E-mail
     */
    @Email
    private String email;

    /**
     * Member that this member information belongs
     */
    private MemberDto member;

    /**
     * Member's favorite board
     */
    private List<BoardDto> boards;
}
