package com.test.repository;

import java.util.List;

import com.test.model.UserDetail;

public interface CustomUserDetailRepository {

	public List<UserDetail> validateUser(String name, String password);

	long increaseCounter(String collectionName);
	
	
}
