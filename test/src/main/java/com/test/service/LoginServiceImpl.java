package com.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.model.UserDetail;
import com.test.repository.UserDetailRepository;

@Service
public class LoginServiceImpl implements ILoginService {

	@Autowired
	UserDetailRepository userDetailRepository;

	public boolean isValidUser(final String userName,final String password) throws Exception {
		List<UserDetail> userDetailList = userDetailRepository.validateUsers(userName, password);
		if (userDetailList != null) {
			return (userDetailList.size() > 0);
		}
		return false;
	}
}
