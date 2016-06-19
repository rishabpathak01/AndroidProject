package com.citrix.training.consumer.test;

import org.easymock.EasyMock;
import org.easymock.IAnswer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.citrix.training.consumer.ApplicationUserService;
import com.citrix.training.consumer.RestResponse;
import com.citrix.training.consumer.User;
import com.citrix.training.consumer.rest.RestUtilityService;

public class ApplicationUserServiceTest {

	private ApplicationUserService applicationUserService;

	private RestUtilityService restUtilityService;

	private String url = "http://localhost:8080/training";

	@Before
	public void initTest() {
		System.out.println("..init..");
		if(applicationUserService == null){
		restUtilityService = EasyMock.createMock(RestUtilityService.class);
		applicationUserService = new ApplicationUserService(restUtilityService,
				url);
		}
	}

	@Test
	public void testGetUserById() {
		User user = new User(2L, "shiv", "prasad");
		RestResponse<User> userResponse = new RestResponse<User>(user);
		EasyMock.expect(restUtilityService.get(url+"/api/user/2", User.class)).andReturn(
				userResponse);
		EasyMock.replay(restUtilityService);
		User finalUser = applicationUserService.getUser(2L);
		Assert.assertEquals(finalUser.getFirstName(), "shiv");
		Assert.assertEquals(finalUser.getLastName(), "prasad");
	}
	
	@Test
	public void testGetUserByIdAndAnswer() {
		EasyMock.expect(restUtilityService.get(url+"/api/user/2", User.class)).andAnswer(new IAnswer<RestResponse<User>>() {
			public RestResponse<User> answer() throws Throwable {
				User user = new User(2L, "shiv", "prasad");
				RestResponse<User> userResponse = new RestResponse<User>(user);
				return userResponse;
			}
			
		});
		EasyMock.replay(restUtilityService);
		User finalUser = applicationUserService.getUser(2L);
		Assert.assertEquals(finalUser.getFirstName(), "shiv");
		Assert.assertEquals(finalUser.getLastName(), "prasad");
	}

	@Test
	public void testDeleteUserById() {
		restUtilityService.delete(url);
		EasyMock.expectLastCall();
		EasyMock.expect(restUtilityService.get(url+"/api/user/2", User.class))
				.andReturn(null);
		EasyMock.replay(restUtilityService);
		applicationUserService.delete(2L);
		User finalUser = applicationUserService.getUser(2L);
		Assert.assertNull(finalUser);
	}

	@Test
	public void testGetUserByIdAndThrowException() {
		User user = new User(2L, "shiv", "prasad");
		RestResponse<User> userResponse = new RestResponse<User>(user);
		EasyMock.expect(restUtilityService.get(url+"/api/user/2", User.class))
				.andReturn(userResponse)
				.andThrow(new RuntimeException("Failed To Connect"));
		EasyMock.replay(restUtilityService);
		try {
			applicationUserService.getUser(2L);
		} catch (Exception e) {
			Assert.assertEquals("Failed To Connect", e.getMessage());
		}
	}
	
	@Test
	public void testGetUserByIdFalseVerify() {
		User user = new User(2L, "shiv", "prasad");
		RestResponse<User> userResponse = new RestResponse<User>(user);
		EasyMock.expect(restUtilityService.get(url+"/api/user/2", User.class))
				.andReturn(userResponse).andThrow(new RuntimeException("Failed To Connect")).times(2);
		EasyMock.replay(restUtilityService);
		try {
			applicationUserService.getUser(2L);
		} catch (Exception e) {
			Assert.assertEquals("Failed To Connect", e.getMessage());
		}
	}

}
