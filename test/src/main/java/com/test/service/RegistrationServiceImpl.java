package com.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.model.UserDetail;
import com.test.repository.UserDetailRepository;

@Service
public class RegistrationServiceImpl implements RegistrationService {

	@Autowired
	UserDetailRepository userDetailRepository;

	public boolean addUserDetails(final UserDetail userDetail) throws Exception {
		UserDetail detail = userDetailRepository.save(userDetail);
		if (null != detail) {
			return true;
		} else {
			return false;
		}
	}
}
