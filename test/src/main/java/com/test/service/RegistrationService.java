package com.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.test.model.UserDetail;
import com.test.repository.IUserDetailDao;
import com.test.repository.UserDetailRepository;

@Service
public class RegistrationService implements IRegistrationService {

	@Autowired
	UserDetailRepository userDetailRepository;

	public String addUserDetails(final UserDetail userDetail) throws Exception {
		UserDetail detail = userDetailRepository.save(userDetail);
		if (null != detail) {
			return "success";
		} else {
			return "fail";
		}
	}
}
