package com.iconnect.profiling.exceptions;

public class RegistrationFailureException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;

	public RegistrationFailureException() {
		super();
	}

	public RegistrationFailureException(String message) {
		super(message);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
