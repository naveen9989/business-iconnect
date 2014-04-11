package com.iconnect.profiling.exceptions;

public class ActivationFailureException extends Exception {
	private static final long serialVersionUID = 1L;
	private String message;

	public ActivationFailureException() {
		super();
	}

	public ActivationFailureException(String message) {
		super(message);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
