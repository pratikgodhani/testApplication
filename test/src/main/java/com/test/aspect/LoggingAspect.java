package com.test.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class LoggingAspect {
	
	@Before("execution(* com.test.service.AccountService.getAccountFeatures(..))")
    public void accountAspect() { 
		System.out.println("Before AccountService, Aspect is invoked...");
	}   
	@Before("execution(* com.test.service.LoginServiceImpl.isValidUser(..))")
	public void loginAspects(){
		System.out.println("Before LoginService, Aspect is invoked...");
	}
}
