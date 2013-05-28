package com.test.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.test.service.ILoginService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	Environment env;
	@Autowired
	ILoginService loginService;

	@Override
	public Authentication authenticate(Authentication authentication)throws AuthenticationException {
		try {
			boolean result = loginService.isValidUser(authentication.getName(), authentication.getCredentials().toString());
			if (!result) {
				return null;
			}
		} catch (Exception e) {
			// TODO - Log the exception into log file.
		}
		return authentication;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}
}
