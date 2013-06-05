package com.test.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.test.Constants;
import com.test.model.UserDetail;

/**
 * This is custom implementation of custom repository.
 * There is a specific naming convention followed while implementing custom repository
 * The name is specified as follow:
 * repositorynameImpl
 * where repositoryname is the name of the repository interface and not the custom repository interface
 * 
 * The name is specified in Repository annotation
 * otherwise name the custom implementation class itself according to naming conventions. Then the name inside repository 
 * annotation can be skipped 
 * 
 *
 */
@Repository("userDetailRepositoryImpl")
public class CustomUserDetailRepositoryImpl implements CustomUserDetailRepository{

	@Autowired
	MongoTemplate mongoTemplate;
	
	@Override
	public List<UserDetail> validateUser(String name, String password) {
		
		Query query=new Query().addCriteria(Criteria.where("userName").is(Constants.USER_PREFIX+name).andOperator(Criteria.where("userPwd").is(password)));
		
		return mongoTemplate.find(query,UserDetail.class);
		
	}
	
	/*
	 * The save method in UserDetailRepository can be overriden by specifying it in custom interface and implementing it here
	 * 
	 * @Override
	public UserDetail save(UserDetail userDetail) {

		System.out.println("11111111111111111111111111111111111111111");
		//SimpleMongoRepository<T, Serializable>
		mongoTemplate.save(userDetail.getAddress());
		mongoTemplate.save(userDetail);
		//mongoTemplate.save(userDetail.getAddress(), Address.class);
		return userDetail;
	}*/

}
