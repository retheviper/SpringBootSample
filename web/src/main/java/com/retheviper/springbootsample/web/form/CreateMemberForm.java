package com.retheviper.springbootsample.web.form;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CreateMemberForm implements Serializable {

	@NotEmpty
	@Size(min = 4, max = 16)
	private String memberId;

	@NotEmpty
	@Size(min = 4, max = 16)
	private String realIdentity;

	@NotEmpty
	@Size(min = 4, max = 16)
	private String password;
}
