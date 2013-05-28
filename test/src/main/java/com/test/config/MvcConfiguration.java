package com.test.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.velocity.VelocityConfig;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;

import com.test.security.CustomAuthenticationProvider;

@Configuration
@ComponentScan(basePackages = "com.test", 
				excludeFilters={@Filter(value=Service.class), @Filter(value=Repository.class),
				@Filter(value=CustomAuthenticationProvider.class, type=FilterType.ASSIGNABLE_TYPE),
				@Filter(value=AppConfiguration.class, type=FilterType.ASSIGNABLE_TYPE)})
@EnableWebMvc
public class MvcConfiguration extends WebMvcConfigurerAdapter {
	@Autowired
	ApplicationVelocityUtil applicationVelocityUtil;

	@Bean
	public VelocityConfig velocityConfig() {
		VelocityConfigurer cfg = new VelocityConfigurer();
		cfg.setResourceLoaderPath("/WEB-INF/views/");
		return cfg;
	}

	@Bean
	public ViewResolver viewResolver() {
		VelocityViewResolver viewResolver = new VelocityViewResolver();
		viewResolver.setPrefix("");
		viewResolver.setSuffix(".vm");
		viewResolver.setExposeSpringMacroHelpers(true);
		/*Map<String, Object> attributes = new HashMap<String, Object>();
		attributes.put("application", applicationVelocityUtil);*/
		//viewResolver.setAttributesMap(attributes);
		return viewResolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
}
