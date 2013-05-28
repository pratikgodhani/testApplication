package com.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.model.UserDetail;
import com.test.repository.UserDetailRepository;

@Service
public class LoginServiceImpl implements ILoginService {

	@Autowired
	UserDetailRepository userDetailRepository;

	@Override
	public String validateUserDetails(final String userName,
			final String password) throws Exception {
		String result = "fail";
		
		/*List<UserDetail> userDetailList = userDetailRepository.findByUserNameAndUserPwd(
				userName, password);*/
		
		List<UserDetail> userDetailList = userDetailRepository.validateUsers(userName, password);

		
		
		if (null != userDetailList && userDetailList.size() > 0) {
			result = "success";
		}
		return result;
	}

}
