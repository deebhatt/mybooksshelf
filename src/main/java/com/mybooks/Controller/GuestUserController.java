package com.mybooks.Controller;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mybooks.commons.ResponseMessage;
import com.mybooks.service.GuestUserService;
import com.mybooks.utility.PropertiesUtil;

@Controller
public class GuestUserController {
	
	private static final Log LOG = LogFactory
			.getLog(GuestUserController.class);
	
	@Inject
	private GuestUserService guestuserService;
	
	@RequestMapping(value = "/sendToken", method = RequestMethod.POST)
	@ResponseBody
	public ResponseMessage sendverificationToken(@RequestBody String mobileNo, HttpServletResponse response)
	{
		ResponseMessage message = guestuserService.sendVerificationTokentoUser(mobileNo);
		Cookie cookie = new Cookie(PropertiesUtil.getProperty("cookie_guestUser"), message.getId());
		cookie.setMaxAge(600);
		response.addCookie(cookie);
		return new ResponseMessage(message.getType(), message.getText());
	}
	
	@RequestMapping(value = "/verifyToken", method = RequestMethod.POST)
	@ResponseBody
	public ResponseMessage verifyToken(@RequestBody Long token, @CookieValue(value="verify_guestuser", defaultValue = "CookieNotFound") String cookieSecret)
	{
		return guestuserService.verifyGuestUSerToken(token, cookieSecret);
	}

}
