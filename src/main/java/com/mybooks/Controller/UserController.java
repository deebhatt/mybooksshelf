package com.mybooks.Controller;

import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mybooks.commons.ResponseMessage;
import com.mybooks.entities.UserMaster;
import com.mybooks.exception.EmailNotFoundException;
import com.mybooks.mbeans.UserFormBean;
import com.mybooks.service.RegistrationService;
import com.mybooks.service.UserService;

@Controller
public class UserController {
	
	private static final Log LOG = LogFactory
			.getLog(UserController.class);
	
	@Inject
	private RegistrationService registrationService;
	
	@Inject
	private UserService userService;

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
	
	@RequestMapping(value = "/changepassword", method = { RequestMethod.POST })
	@ResponseBody
	public ResponseMessage changePassword(@RequestBody UserFormBean userFormBean) {
		return registrationService.changePassword(userFormBean);
	}
	
	@RequestMapping(value = "/getUser", method = { RequestMethod.GET,
			RequestMethod.POST})
	@ResponseBody
	public UserFormBean getUser() {
		UserMaster user;
		try {
			user = userService.getLoggedInUser();
		
			UserFormBean bean = new UserFormBean();
			bean.setEmail(user.getEmail());
			bean.setFirstName(user.getFirstName());
			bean.setLastName(user.getLastName());
		return bean;
		} catch (EmailNotFoundException e) {
			LOG.error(e);
			return null;
		}
	}

}
