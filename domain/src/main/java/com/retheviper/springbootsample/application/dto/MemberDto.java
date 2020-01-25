package com.retheviper.springbootsample.application.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto implements Serializable {

	private Long id;

	private String memberId;

	private String realIdentity;

	private String password;

	private LocalDateTime joinedDate;
}
