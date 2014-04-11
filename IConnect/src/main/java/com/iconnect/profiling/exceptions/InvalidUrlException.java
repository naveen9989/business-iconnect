package com.iconnect.profiling.exceptions;

public class InvalidUrlException extends Exception {
	private static final long serialVersionUID = 1L;
	private String message;

	public InvalidUrlException() {
		super();
	}

	public InvalidUrlException(String message) {
		super(message);
		this.message = message;
	}

	public InvalidUrlException(Throwable cause) {
		super(cause);
	}

	public String getMessage() {
		return message;
	}

}
