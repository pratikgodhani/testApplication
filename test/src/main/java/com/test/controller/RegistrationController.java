/**
 * 
 */
package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.test.model.UserDetail;
import com.test.service.IRegistrationService;

/**
 * @author 
 *
 */
@Controller
public class RegistrationController {

	@Autowired
	Environment env;
	
	@Autowired
	IRegistrationService registrationService;
	
	@RequestMapping(value = "/register")
	public ModelAndView getRegistration(Model model) {
		model.addAttribute("user", new UserDetail());
		return new ModelAndView("register");
	}

	@RequestMapping (value = "/addRegistration")
	public @ResponseBody String addRegistrationDetails(@ModelAttribute(value = "SpringWeb") UserDetail userDetail) throws Exception {
		String failedMsg = env.getProperty("registrationFailMsg");
		String successMsg = env.getProperty("registrationSuccessMsg");
		String result1 = registrationService.addUserDetails(userDetail);
		if (result1.equals("success"))
		{
			return successMsg;
		}
		return failedMsg;
	}

}
