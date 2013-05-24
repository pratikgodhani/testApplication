package com.test.controller;

import org.hamcrest.core.IsAnything;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.test.model.UserDetail;
import com.test.service.ILoginService;
import com.test.service.LoginServiceImpl;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class LoginControllerSpec {

	@InjectMocks
	LoginController loginController = new LoginController ();
	@Mock Model model;
	@Mock UserDetail userDetail;
	@Mock ILoginService loginService;
	
	@Before
	public void setup()
	{
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void givenLoginUrlShouldReturnLoginView (){
		
		//when
		ModelAndView mv = loginController.login(model);
		
		assertThat(mv.getViewName()).isEqualTo("login");
		
		verify(model).addAttribute(eq("user"), isA(UserDetail.class));
	}
	
	@Test
	public void givenUserNameAndPasswordShouldBeValid() throws Exception
	{
		//Given
		String result = "success";
		
		//When
		when(loginService.validateUserDetails("p", "p")).thenReturn(result);
		String msg = loginController.validateUserDetails (userDetail);
		
		//Then
		assertThat(msg).isEqualTo("You have successfully logged in.");
	}
	
	@Test
	public void givenUserNameAndPasswordShouldBeFail () throws Exception
	{
		//Given
		String result = "fail";
		
		//When
		when(loginService.validateUserDetails("p", "p")).thenReturn(result);
		String msg = loginController.validateUserDetails (userDetail);
		
		//Then
		assertThat(msg).isEqualTo("Sorry, Wrong username/password.");
	}
}
