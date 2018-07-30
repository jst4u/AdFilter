package com.loit.core.security;

import org.springframework.security.AuthenticationException;

public class ValidationCodeErrorException extends AuthenticationException {
	private static final long serialVersionUID = 1L;

	public ValidationCodeErrorException() {
		super("Validation Code Error");
	}
}
