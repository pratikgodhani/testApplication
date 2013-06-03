/**
 * 
 */
package com.test.config;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.web.context.ConfigurableWebApplicationContext;

/**
 * @author cts1
 *
 */
public class ProfileInitializer implements ApplicationContextInitializer<ConfigurableWebApplicationContext > {



	@Override
	public void initialize(ConfigurableWebApplicationContext applicationContext) {

		String activeProfile=System.getenv("myapp.active.profile");
		System.out.println("Active profiles: "+activeProfile);
		applicationContext.getEnvironment().setActiveProfiles(activeProfile);
	}

}
