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

import com.test.Constants;
import com.test.model.UserDetail;
import com.test.service.RegistrationService;

/**
 * @author 
 *
 */
@Controller
public class RegistrationController {



	@Autowired
	Environment env;
	
	@Autowired
	RegistrationService registrationService;
	
	@RequestMapping(value = "/register")
	public ModelAndView getRegistration(Model model) {
		model.addAttribute("user", new UserDetail());
		return new ModelAndView("register");
	}

	@RequestMapping (value = "/addRegistration")
	public @ResponseBody String addRegistrationDetails(@ModelAttribute(value = "SpringWeb") UserDetail userDetail) throws Exception {
		String failedMsg = env.getProperty("registrationFailMsg");
		String successMsg = env.getProperty("registrationSuccessMsg");
		userDetail.setUserName(Constants.USER_PREFIX+userDetail.getUserName());
		boolean result = registrationService.addUserDetails(userDetail);
		if (result)
		{
			return successMsg;
		}
		return failedMsg;
	}

}
