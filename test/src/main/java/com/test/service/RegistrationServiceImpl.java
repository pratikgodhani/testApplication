package com.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.model.Address;
import com.test.model.UserDetail;
import com.test.repository.AddressRepository;
import com.test.repository.UserDetailRepository;

@Service
public class RegistrationServiceImpl implements RegistrationService {

	@Autowired
	UserDetailRepository userDetailRepository;

	@Autowired
	AddressRepository addressRepository;

	public boolean addUserDetails(final UserDetail userDetail) throws Exception {
		userDetail.setAutoId(userDetailRepository.increaseCounter("users"));
		userDetailRepository.save(userDetail);
		return true;
	}

	public boolean addAddress(Address address, final String userId)
			throws Exception {

		UserDetail userDetail = userDetailRepository.findOne(userId);
		address = addressRepository.save(address);
		userDetail.setAddress(address);
		userDetailRepository.save(userDetail);
		if (null != address) {
			return true;
		}
		return false;
	}

}
