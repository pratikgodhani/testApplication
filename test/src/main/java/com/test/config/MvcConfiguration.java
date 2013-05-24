package com.test.config;

import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.velocity.VelocityConfig;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;

import com.mongodb.MongoClient;

@Configuration
@ComponentScan(basePackages = "com.test")
@EnableWebMvc
@PropertySource({"registration.properties", "database.properties"})
@EnableMongoRepositories(basePackages = "com.test.repository")
public class MvcConfiguration extends WebMvcConfigurerAdapter {

	@Autowired
	Environment env;
	@Autowired
	ApplicationVelocityUtil applicationVelocityUtil;
	
	@Autowired
	MongoDbFactory mongoDbFactory;

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

	@Bean
	public MongoDbFactory mongoDbFactory() throws UnknownHostException {
		MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(
				new MongoClient(env.getProperty("host"), 27017),
				env.getProperty("databaseName"));
		return mongoDbFactory;
	}

	@Bean
	public MongoTemplate mongoTemplate() throws UnknownHostException {
		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory);
		// mongoTemplate.set
		return mongoTemplate;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations(
				"/resources/");
	}
}
