package com.test.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;
import org.springframework.stereotype.Repository;

import com.test.Constants;
import com.test.model.Address;
import com.test.model.UserDetail;


@Repository("userDetailRepositoryImpl")
public class CustomUserDetailRepositoryImpl implements CustomUserDetailRepository{

	@Autowired
	MongoTemplate mongoTemplate;
	
	@Override
	public List<UserDetail> validateUser(String name, String password) {
		
		Query query=new Query().addCriteria(Criteria.where("userName").is(Constants.USER_PREFIX+name).andOperator(Criteria.where("userPwd").is(password)));
		
		return mongoTemplate.find(query,UserDetail.class);
		
	}
	
	/*@Override
	public UserDetail save(UserDetail userDetail) {

		System.out.println("11111111111111111111111111111111111111111");
		//SimpleMongoRepository<T, Serializable>
		mongoTemplate.save(userDetail.getAddress());
		mongoTemplate.save(userDetail);
		//mongoTemplate.save(userDetail.getAddress(), Address.class);
		return userDetail;
	}*/

}
