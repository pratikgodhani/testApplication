package com.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.test.model.UserDetail;
import com.test.repository.UserDetailRepository;

/**
 * This is LoginService implementation
 * There are two login service implementations in package.
 * Both are named loginServiceImpl
 * 
 * These are two different Implementations for two different profiles.
 * Whenever a specified profile is loaded the service implementation class
 * specified using profile annotation will be loaded
 * 
 * The name of the profile is specified in brackets
 * 
 * The active profile is set while initializing the application
 * It can be specified using activeprofile annotation on configuration class
 * or we can use custom application context initializer which will be called 
 * while initializing the application which intern will set the active profile by reading it from
 * property file or system environment var
 * 
 * refer @link ProfileInitializer
 * 
 *
 */
@Service("loginServiceImpl")
@Profile("dev")
public class LoginServiceImpl implements LoginService {

	@Autowired
	UserDetailRepository userDetailRepository;

	public boolean isValidUser(final String userName,final String password) throws Exception {
		System.out.println("DEV");
		List<UserDetail> userDetailList = userDetailRepository.validateUser(userName, password);
		if (userDetailList != null) {
			return (userDetailList.size() > 0);
		}
		return false;
	}
}
