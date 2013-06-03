package com.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.test.model.UserDetail;
import com.test.repository.UserDetailRepository;

@Service("loginServiceImpl")
@Profile("prod")
public class LoginServiceProdImpl implements LoginService {

	@Autowired
	UserDetailRepository userDetailRepository;

	public boolean isValidUser(final String userName,final String password) throws Exception {
		System.out.println("Production implementation of Login service");
		List<UserDetail> userDetailList = userDetailRepository.validateUser("PROD_"+userName, password);
		if (userDetailList != null) {
			return (userDetailList.size() > 0);
		}
		return false;
	}
}
