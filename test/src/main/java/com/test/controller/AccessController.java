package com.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.service.AccountService;

@Controller
public class AccessController {
	
	@Autowired
	AccountService accountService;
	
	@RequestMapping(value="/user")
	public String userPage(Model model){
		List features = accountService.getAccountFeatures();
		model.addAttribute("features",features);
		return "user";
	}
	
	@RequestMapping(value="/admin")
	public String adminPage(){
		return "admin";
	}
}
