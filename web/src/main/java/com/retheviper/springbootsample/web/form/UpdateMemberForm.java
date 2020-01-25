package com.retheviper.springbootsample.web.form;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UpdateMemberForm implements Serializable {

	@NotEmpty
	@Size(min = 4, max = 16)
	private String password;
}
