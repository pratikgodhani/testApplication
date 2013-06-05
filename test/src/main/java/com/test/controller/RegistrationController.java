/**
 * 
 */
package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.test.Constants;
import com.test.model.Address;
import com.test.model.FormErrors;
import com.test.model.UserDetail;
import com.test.model.UserDetailValidator;
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
	UserDetailValidator validator;

	@Autowired
	RegistrationService registrationService;

	@RequestMapping(value = "/register")
	public ModelAndView getRegistration(Model model) {
		model.addAttribute("user", new UserDetail());
		return new ModelAndView("register");
	}

	@RequestMapping(value = "/addRegistration")
	public @ResponseBody
	FormErrors addRegistrationDetails(
			@ModelAttribute(value = "user") UserDetail userDetail,
			BindingResult bindingResult) throws Exception {

		FormErrors formErrors = new FormErrors();

		validator.validate(userDetail, bindingResult);
		if (bindingResult.hasErrors()) {
			formErrors.setStatus("Fail");
			formErrors.setResult(bindingResult.getFieldErrors());
			return formErrors;
		} else {
			userDetail.setUserName(Constants.USER_PREFIX
					+ userDetail.getUserName());
			boolean result = registrationService.addUserDetails(userDetail);
			formErrors.setStatus("Fail");
			if (result) {
				formErrors.setResult(userDetail.getId());
				formErrors.setUserId(userDetail.getId().toString());
				formErrors.setStatus("Pass");
			}
			return formErrors;
		}
	}

	@RequestMapping(value = "/address/{userId}", method = RequestMethod.GET)
	public ModelAndView getAddress(ModelMap model, @PathVariable String userId) {
		model.addAttribute("address", new Address());
		model.addAttribute("userId", userId);
		return new ModelAndView("address");
	}

	@RequestMapping(value = "/address/{userId}", method = RequestMethod.POST)
	public @ResponseBody
	FormErrors getAddress(@ModelAttribute(value="address") Address address, BindingResult bindingResult, @PathVariable String userId) throws Exception {
		FormErrors formErrors = new FormErrors();
		boolean result = registrationService.addAddress(address, userId);
		formErrors.setStatus("Fail");
		if (result)
		{
			formErrors.setStatus("Pass");
		}
		return formErrors;
	}

}
