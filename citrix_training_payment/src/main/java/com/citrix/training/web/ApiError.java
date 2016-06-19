package com.citrix.training.web;

import java.io.Serializable;

public class ApiError implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8168202298107177134L;
	
	private int statusCode;
	
	private String message;

	public ApiError() {
		super();
	}

	public ApiError(int statusCode, String message) {
		super();
		this.statusCode = statusCode;
		this.message = message;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
