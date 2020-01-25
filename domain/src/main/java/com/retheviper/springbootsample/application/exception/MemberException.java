package com.retheviper.springbootsample.application.exception;

public class MemberException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1097447630123895615L;

	public MemberException(String message) {
		super(message);
	}
}
