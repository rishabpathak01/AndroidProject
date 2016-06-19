package com.citrix.training.consumer;

public class RestResponse<T> {

	private final T responseObject;

	private int httpStatusCode = 200;

	public RestResponse(T responseObject) {
		this.responseObject = responseObject;
	}

	public RestResponse(T responseObject, int httpStatusCode) {
		this.responseObject = responseObject;
		this.httpStatusCode = httpStatusCode;
	}

	public T getResponseObject() {
		return responseObject;
	}

	public int getHttpStatusCode() {
		return httpStatusCode;
	}

}
