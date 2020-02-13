package com.retheviper.springbootsample.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionMessageConstant {

	/** Data is null */
	COMMON_E000("Non data Exists"),

	/** Password is incorrect */
	COMMON_E001("Password Unmatch"),

	/** Password did not changed */
	COMMON_E002("Password did not changed"),

	/** Password not inputed */
	COMMON_E003("Password did not inputed"),

	/** Member ID already exists */
	MEMBER_E000("ID already exists"),

	/** Member ID does not exists */
	MEMBER_E001("ID does not exists"),

	/** Article already exists */
	ARTICLE_E000("Article already exists"),

	/** Article does not exists */
	ARTICLE_E001("Article does not exists");

	private final String message;
}
