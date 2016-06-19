package com.citrix.training.consumer.test;

import org.junit.Test;

import com.citrix.training.consumer.User;
import com.citrix.training.consumer.rest.RestUtilityService;
import com.citrix.training.consumer.rest.RestUtilityServiceImpl;

public class RestUtilServiceTest {
	
	private RestUtilityService restUtilityService = new RestUtilityServiceImpl();
	
	@Test
	public void testGet(){
		String url = "http://localhost:8080/training/api/user/2";
		restUtilityService.get(url, User.class);
	}

}
