package com.test.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.velocity.VelocityConfig;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;

import com.test.security.CustomAuthenticationProvider;
import com.test.security.VelocitySecurityWrapper;
/**
 * This is a configuration class for annotation based spring configuration.
 * Instead of configuring Spring through XML this annotation based approach can be used.
 * 
 * To mark a class as a configuration class the annotaion {@literal @}configuration is used
 * 
 * ComponentScan- The component scan annotation specified the package which should be scanned for any components or services
 * Filters can be used to exclude a particular class.
 * 
 * EnableWebMVC - this annotation enables MVC related annotations like {@literal @}ResponseBody
 * 
 *
 */
@Configuration
@ComponentScan(basePackages = "com.test", 
				excludeFilters={@Filter(value=Service.class), @Filter(value=Repository.class),
				@Filter(value=CustomAuthenticationProvider.class, type=FilterType.ASSIGNABLE_TYPE),
				@Filter(value=AppConfiguration.class, type=FilterType.ASSIGNABLE_TYPE)})
@EnableWebMvc
public class MvcConfiguration{

	

	@Bean
	public VelocityConfig velocityConfig() {
		VelocityConfigurer cfg = new VelocityConfigurer();
		cfg.setResourceLoaderPath("/WEB-INF/views/");//set this to where your velocity template files are placed
		return cfg;
	}

	/**
	 * while using velocity view resolver it is required to configure velocityConfig bean as defined above 
	 * @return
	 */
	@Bean
	public ViewResolver viewResolver() {
		VelocityViewResolver viewResolver = new VelocityViewResolver();
		viewResolver.setPrefix("");	//no prefix is specified here as the resourceloader path already points to /WEB-INF/views/ 
									//as defined in velocityConfig() bean above
		
		viewResolver.setSuffix(".vm"); //this suffix will be appended to view name. In case of JSP it will be .jsp
		
		viewResolver.setExposeSpringMacroHelpers(true);//this will enable use of some useful macros while writing velocity template
		//e.g. #springUrl('url')
		
		VelocitySecurityWrapper securityWrapper = new VelocitySecurityWrapper();
		Map<String, VelocitySecurityWrapper> attributes = new HashMap<String, VelocitySecurityWrapper>();
	    attributes.put("vsecurity", securityWrapper);
		viewResolver.setAttributesMap(attributes);
		
		
		return viewResolver;
	}

}
