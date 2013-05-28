package com.test.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.test.model.UserDetail;

@Repository
public interface UserDetailRepository extends CrudRepository<UserDetail, String>, UserRepositoryCustom {
	
	List<UserDetail> findByUserNameAndUserPwd (final String userName, final String userPwd);
	/*

	@Autowired MongoTemplate mongoTemplate;
	
	@Override
	public String addUserDetails(UserDetail userDetail) throws Exception {
		String result = "fail";
		try {
			userDetail.setId(UUID.randomUUID().toString());
			mongoTemplate.insert(userDetail);
			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
			result = "fail";
			throw e;
		}
		return result;
	}

	@Override
	public String updateUserDetails(UserDetail userDetail) throws Exception {
		String result = "fail";
		try {
			mongoTemplate.insert(userDetail);
			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
			result = "fail";
			throw e;
		}
		return result;
	}

	@Override
	public String deleteUserDetails(UserDetail userDetail) throws Exception {
		String result = "fail";
		try {
			mongoTemplate.remove(userDetail);
			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
			result = "fail";
			throw e;
		}
		return result;
	}

	@Override
	public UserDetail fetchUserDetailById(String id) throws Exception {
		UserDetail userDetail;
		try {
			userDetail = mongoTemplate.findById(id, UserDetail.class);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return userDetail;
	}

	@Override
	public List<UserDetail> fetchAllUserDetails() throws Exception {
		List<UserDetail> userDetails = new ArrayList<UserDetail>();
		try {
			userDetails = mongoTemplate.findAll(UserDetail.class);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return userDetails;
	}

	@Override
	public UserDetail fetchUserDetailByUserNamePassword(final String userName,
			final String password) throws Exception {
		UserDetail userDetail;
		try {
			Query query = new Query(Criteria.where("userName").is(userName).andOperator(Criteria.where("userPwd").is(password)));
			userDetail = mongoTemplate.findOne(query, UserDetail.class);
		} catch (final Exception e) {
			e.printStackTrace();
			throw e;
		}
		return userDetail;
	}*/
}
