package com.citrix.training.consumer.rest;

import com.citrix.training.consumer.RestResponse;

public interface RestUtilityService {
	
	public <T> RestResponse<T> get(String url, Class<T> responseType);
	
	public <T> RestResponse<T> post(String url, Class<T> responseType);
	
	public <T> RestResponse<T> put(String url, Class<T> responseType);
	
	public void delete(String url);
	

}