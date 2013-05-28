package com.test.repository;

import java.util.List;

import com.test.model.UserDetail;

public interface UserRepositoryCustom {

	public List<UserDetail> validateUsers(String name, String password);
	
	
}
