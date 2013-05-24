package com.test.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ApplicationVelocityUtil {

	@Autowired
	Environment env;

	public String internalURL(String suffix) {

		String internalURL = "";
		internalURL = env.getProperty("applicationContext") + suffix;
		return internalURL;
	}
}
