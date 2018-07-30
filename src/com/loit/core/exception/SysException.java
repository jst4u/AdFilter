package com.loit.core.exception;

public class SysException extends RuntimeException {

	public SysException() {
	}

	public SysException(String message) {
		super(message);
	}

	public SysException(Throwable cause) {
		super(cause);
	}

	public SysException(String message, Throwable cause) {
		super(message, cause);
	}
}
