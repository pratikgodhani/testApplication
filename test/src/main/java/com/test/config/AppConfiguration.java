package com.test.config;

import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Controller;

import com.mongodb.MongoClient;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "com.test", 
				excludeFilters={@Filter(value=MvcConfiguration.class, type=FilterType.ASSIGNABLE_TYPE), @Filter(value=Controller.class)})
@ImportResource( { "classpath:spring-security.xml" } )
@PropertySource({"registration.properties", "database.properties"})
@EnableMongoRepositories(basePackages = "com.test.repository")

public class AppConfiguration {

	
	/*
	 * 
	 * This env variable can be used to access properties from all the property files present in src/main/resources folder
	 * To make the properties available to env one has to specify them using @PropertySource annotation as specified above
	 * This variable is autowired and loads all the properties automatically
	 * One can access property values using env.getProperty("propertyKey")
	 */
	@Autowired
	Environment env;
	
	@Autowired
	MongoDbFactory mongoDbFactory;

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
	
		return mongoTemplate;
	}
}
