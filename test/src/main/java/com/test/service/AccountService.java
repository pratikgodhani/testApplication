package com.test.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.test.security.VelocitySecurityWrapper;

@Service
public class AccountService {

	public List getAccountFeatures(){
		List<String> features = new ArrayList<String>();
		if(VelocitySecurityWrapper.isAuthorizedUser()){
			features.add("USER_FEATURE1");
			features.add("USER_FEATURE2");
			features.add("USER_FEATURE3");
			features.add("USER_FEATURE4");
		}
		if(VelocitySecurityWrapper.isAuthorizedAdmin()){
			features.add("ADMIN_FEATURE1");
			features.add("ADMIN_FEATURE2");
			features.add("ADMIN_FEATURE3");
		}
		return features;
	}
}
