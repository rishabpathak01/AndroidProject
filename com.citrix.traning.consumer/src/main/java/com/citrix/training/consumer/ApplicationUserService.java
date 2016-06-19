package com.citrix.training.consumer;

import com.citrix.training.consumer.rest.RestUtilityService;

public class ApplicationUserService {

	private final RestUtilityService restUtilityService;

	private final String url;
	
	private final String GET_USER_URL = "/api/user/";

	public ApplicationUserService(RestUtilityService restUtilityService,
			String url) {
		this.restUtilityService = restUtilityService;
		this.url = url;
	}

	public User getUser(Long id) {
		RestResponse<User> restResponse = restUtilityService.get(url+GET_USER_URL+id,
				User.class);
		return restResponse != null? restResponse.getResponseObject():null;
	}

	public void delete(Long id) {
		restUtilityService.delete(url);
	}

	public RestUtilityService getRestUtilityService() {
		return restUtilityService;
	}

}
