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
		if(activeProfile==null){
			System.out.println("Please set system environment variable 'myapp.active.profile'");
			System.out.println("Possible profiles are dev/prod");
			System.exit(1);
		}else{
			System.out.println("Active profiles: "+activeProfile);
			applicationContext.getEnvironment().setActiveProfiles(activeProfile);	
		}
		
	}

}
