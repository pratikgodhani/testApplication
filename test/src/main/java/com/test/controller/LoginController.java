package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.test.model.UserDetail;
import com.test.service.ILoginService;

@Controller
public class LoginController {

	@Autowired
	Environment env;
	@Autowired
	ILoginService loginService;

	@RequestMapping("/login")
	public ModelAndView login(Model model) {
		model.addAttribute("user", new UserDetail());
		return new ModelAndView("login");
	}
}
