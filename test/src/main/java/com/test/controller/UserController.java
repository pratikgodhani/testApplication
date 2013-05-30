package com.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
	
	@RequestMapping(value="/user")
	public String userPage(){
		return "user";
	}
	
	@RequestMapping(value="/admin")
	public String adminPage(){
		return "admin";
	}
}
