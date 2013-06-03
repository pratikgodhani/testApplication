package com.test.controller;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.env.Environment;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import com.test.model.FormErrors;
import com.test.model.UserDetail;
import com.test.model.UserDetailValidator;
import com.test.service.RegistrationService;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationControllerSpec {
	
	@InjectMocks
	RegistrationController controller = new RegistrationController();
	
	@Mock Model model;
	@Mock RegistrationService registrationService;
	@Mock UserDetail userDetail;
	@Mock Environment env;
	@Mock UserDetailValidator validator;
	
	@Mock BindingResult bindingResult;
	
	@Before
	public void setup()
	{
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void givenRegistrationUrlShouldReturnRegistrationView() {
		//When
		ModelAndView mv = controller.getRegistration(model);

		//Then
		assertThat(mv.getViewName()).isEqualTo("register");
		verify(model).addAttribute(eq("user"), isA(UserDetail.class));
	}
	
	@Test
	public void givenRegistrationDetailsShouldGetSavedAndReturnSuccessRegistrationView() throws Exception {
		
		//Given
		boolean success = true;
		
		
		
		//When
		when(registrationService.addUserDetails(userDetail)).thenReturn(success);
		when(env.getProperty("registrationSuccessMsg")).thenReturn("User has been added to the list.");
		when(bindingResult.hasErrors()).thenReturn(false);
		
		FormErrors errors = controller.addRegistrationDetails(userDetail,bindingResult);
		assertThat(errors.getStatus()).isEqualTo("Pass");
		
		
		
	}
	
	@Test
	public void givenRegistrationdetailsShouldReturnFail() throws Exception {
		
		//Given
		boolean fail = false;
		
		//when
		when(registrationService.addUserDetails(userDetail)).thenReturn(fail);
		when(env.getProperty("registrationFailMsg")).thenReturn("Sorry, an error has occur. User has not been added to list.");
		when(bindingResult.hasErrors()).thenReturn(true);
		
		FormErrors errors = controller.addRegistrationDetails(userDetail,bindingResult);
		assertThat(errors.getStatus()).isEqualTo("Fail");
		
	}
}
