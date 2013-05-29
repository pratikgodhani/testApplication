package com.test.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.test.Constants;
import com.test.model.UserDetail;

public class UserDetailRepositoryImpl implements UserRepositoryCustom{

	@Autowired
	MongoTemplate mongoTemplate;
	
	@Override
	public List<UserDetail> validateUsers(String name, String password) {
		
		Query query=new Query().addCriteria(Criteria.where("userName").is(Constants.USER_PREFIX+name).andOperator(Criteria.where("userPwd").is(password)));
		
		return mongoTemplate.find(query,UserDetail.class);
		
	}

}
