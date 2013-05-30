package com.test.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.test.model.UserDetail;

@Repository
public interface UserDetailRepository extends CrudRepository<UserDetail, String>, CustomUserDetailRepository {
	
	List<UserDetail> findByUserNameAndUserPwd (final String userName, final String userPwd);

}
