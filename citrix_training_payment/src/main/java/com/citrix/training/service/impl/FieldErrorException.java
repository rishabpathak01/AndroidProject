package com.citrix.training.service.impl;

public class FieldErrorException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8286627909051582952L;

	public FieldErrorException() {
		super();
	}

	public FieldErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public FieldErrorException(String message, Throwable cause) {
		super(message, cause);
	}

	public FieldErrorException(String message) {
		super(message);
	}

	public FieldErrorException(Throwable cause) {
		super(cause);
	}

	
}
