package com.mybooks.Controller;

import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mybooks.commons.ResponseMessage;
import com.mybooks.mbeans.UserFormBean;
import com.mybooks.service.RegistrationService;

public class UserController {
	
	private static final Log LOG = LogFactory
			.getLog(UserController.class);
	
	@Inject
	private RegistrationService registrationService;

	@RequestMapping(value = "/registeruser", method = RequestMethod.POST)
	@ResponseBody
	public ResponseMessage registerUser(@RequestBody UserFormBean userFormBean) {
		return registrationService.registerUser(userFormBean);
	}
	
	@RequestMapping(value = "/resetpassword", method = { RequestMethod.POST })
	@ResponseBody
	public ResponseMessage resetPassword(@RequestBody UserFormBean userFormBean) {
		return registrationService.resetPassword(userFormBean);
	}

}
