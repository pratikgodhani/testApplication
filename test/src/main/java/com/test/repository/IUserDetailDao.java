package com.test.repository;

import java.util.List;

import com.test.model.UserDetail;

public interface IUserDetailDao {

	String addUserDetails (final UserDetail userDetail) throws Exception;
	
	String updateUserDetails (final UserDetail userDetail) throws Exception;
	
	String deleteUserDetails (final UserDetail userDetail) throws Exception;
	
	UserDetail fetchUserDetailById (final String id) throws Exception;
	
	List<UserDetail> fetchAllUserDetails () throws Exception;
	
	UserDetail fetchUserDetailByUserNamePassword (final String userName, final String password) throws Exception;
}
