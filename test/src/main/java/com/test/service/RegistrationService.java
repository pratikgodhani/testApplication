package com.test.service;

import com.test.model.Address;
import com.test.model.UserDetail;

public interface RegistrationService {

	public boolean addUserDetails(final UserDetail userDetail) throws Exception;

	public boolean addAddress(Address address, final String userId)
			throws Exception;
}
