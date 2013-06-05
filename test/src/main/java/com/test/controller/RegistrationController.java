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

	
	
	/**
	 * This controller is responsible for handling the registration of new user
	 * Form validation is performed before passing the UserDetail bean to service
	 * 
	 *  Response of the controller is FormErrors annotated using {@literal@}ResponseBody
	 *  ResponseBody annotation indicates that a method return value should be bound to the web response body. 
	 * Here we have used JSON and for that we have included Jackson dependency in POM.xml without which view will throw a 406 error
	 * 
	 * FormErrors is  a class which stores status, result. This object is used on view to render result/errors.
	 *  
	 *   
	 * @param userDetail
	 * @param bindingResult
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addRegistration")
	public @ResponseBody FormErrors addRegistrationDetails(
			@ModelAttribute(value = "user") UserDetail userDetail,
			BindingResult bindingResult) throws Exception {

		FormErrors formErrors = new FormErrors();

		/*validate the form using CustomValidator
		 * Validator stores the error messages in bindingResult Object.
		 * All results can be obtained using bindingResult.getAllErrors()
		 * 
		 * To get field specific errors use bindingResult.getFieldErrors()
		 */
		validator.validate(userDetail, bindingResult);
		
		//If there were any errors in validation set errors in formErros and set status to fail 
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
				/*It is important to store userId as string otherwise javascript does not identify it as a string 
				 * and will consider it as an object
				 * 
				 * The userId is passed so that it can be forwarded in next request to address form as a path variable.
				 * There it will be used to map the specified address with the UserDetails associated with given userID
				 * And the userdetails will be updated with id of address which is newly created
				 * This is required as we are storing UserDetails and address separately in separate collections
				 * 
				 */
				formErrors.setResult(userDetail.getId().toString());
				formErrors.setStatus("Pass");
			}
			return formErrors;
		}
	}

	
	/**
	 * userID is passed as path variable 
	 * @param model
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/address/{userId}", method = RequestMethod.GET)
	public ModelAndView getAddress(ModelMap model, @PathVariable String userId) {
		model.addAttribute("address", new Address());
		model.addAttribute("userId", userId);
		return new ModelAndView("address");
	}

	
	
	@RequestMapping(value = "/address", method = RequestMethod.POST)
	public @ResponseBody
	FormErrors getAddress(@ModelAttribute(value="address") Address address, BindingResult bindingResult, @ModelAttribute (value="userId") String userId) throws Exception {
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
