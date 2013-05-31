package com.test.model;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserDetailValidator implements Validator {

	@Override
	public void validate(Object obj, Errors e) {
		
		UserDetail userDetails=(UserDetail) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "userName", "username.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "firstName", "firstname.required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(e,  "userPwd","password.required");
		
		if(userDetails.getUserPwd().length()<5){
			e.rejectValue("userPwd", "Password cannot be less than 6 char long");
		}
	}
	
	@Override
	public boolean supports(Class<?> arg0) {
		
		return UserDetail.class.equals(arg0);
	}

	
	
}
