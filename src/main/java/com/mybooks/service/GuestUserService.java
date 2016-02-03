package com.mybooks.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mybooks.Controller.GuestUserController;
import com.mybooks.commons.ResponseMessage;
import com.mybooks.constants.ApplicationConstants;
import com.mybooks.dao.GuestUserDAO;
import com.mybooks.entities.GuestUser;
import com.mybooks.entities.Roles;
import com.mybooks.entities.UserMaster;
import com.mybooks.enums.USER_ROLES;
import com.mybooks.exception.DAOException;
import com.mybooks.exception.DBRecordNotFoundException;
import com.mybooks.exception.EmailNotFoundException;
import com.mybooks.exception.EmailSenderServiceException;
import com.mybooks.exception.RoleNotFoundException;
import com.mybooks.exception.SmsSenderServiceException;
import com.mybooks.exception.UserServiceException;
import com.mybooks.service.email.EmailSenderService;
import com.mybooks.service.sms.SMSLeadsMessage;
import com.mybooks.service.sms.SMSSenderService;
import com.mybooks.service.sms.SMSSenderServiceImpl;
import com.mybooks.utility.EmailFormatter;
import com.mybooks.utility.PropertiesUtil;
import com.mybooks.utility.SMSFormatter;
import com.mybooks.utility.SecurityUtil;

@Service("guestuserService")
@Repository
public class GuestUserService {

	private static final Log LOG = LogFactory
			.getLog(GuestUserService.class);
	
	@Inject
	private GuestUserDAO guestuserDAO;
	
	@Inject
	private EmailSenderService emailSender;
	
	@Inject
	private SMSSenderService smsSender;
	
	@Inject
	private UserService userService;
	
	@Transactional(rollbackFor=DAOException.class)
	public ResponseMessage sendVerificationTokentoUser(String mobileNo)
	{
		GuestUser guestuser = null;
		int generateToken = 0;
		try {
			int startValue = Integer.parseInt(PropertiesUtil.getProperty("email.token.start"));
			int endValue = Integer.parseInt(PropertiesUtil.getProperty("email.token.end"));
			generateToken = SecurityUtil.generateRandomNumber(startValue, endValue);
			
			guestuser = guestuserDAO.findUserByMobileNo(mobileNo);
			//User already found so update the user
			guestuser.setTokenCreatedDate(System.currentTimeMillis());
			guestuser.setToken((long) generateToken);
			guestuserDAO.update(guestuser);
		} catch (DBRecordNotFoundException e) {
			LOG.debug("Guest User Not Found, so create a new Guest User");
		} catch(DAOException e) {
			LOG.error("There was a technical error while updating Guest User", e);
			return new ResponseMessage(ResponseMessage.Type.danger,
					"There was a technical error while updating Guest User. Please try again");
		}
		try {
			if(guestuser == null) {
				guestuser = new GuestUser();
				guestuser.setMobileNumber(mobileNo);
				guestuser.setToken((long) generateToken);
				guestuser.setTokenCreatedDate(System.currentTimeMillis());
				guestuserDAO.persist(guestuser);
			}
		} catch (DAOException e) {
			LOG.error("There was a technical error while adding Guest User", e);
			return new ResponseMessage(ResponseMessage.Type.danger,
					"There was a technical error while adding Guest User. Please try again");
		}
		// Encode MobileNumber
		String cookieSecret = SecurityUtil.encodeValue(mobileNo);
					
		// SEND EMAIL NOTIFICATION
		/*SimpleMailMessage message = EmailFormatter.generateTokenEmailMessage(String.valueOf(generateEmailToken), guestuser);
		try {
			emailSender.sendEmail(message);
		} catch (EmailSenderServiceException e) {
			LOG.error("Errors while sending COD token", e);
			return new ResponseMessage(ResponseMessage.Type.danger,
					"There was a technical error while sending Email. Please try again");
		}*/
		
		//SEND SMS NOTIFICATION
		/*SMSLeadsMessage message = SMSFormatter.generateTokenforCashOnDelivery(String.valueOf(generateToken), guestuser);
		try {
			smsSender.sendSMS(message);
		} catch (SmsSenderServiceException e) {
			LOG.error("Errors while sending COD token", e);
			return new ResponseMessage(ResponseMessage.Type.danger,
					 "There was a technical error while sending SMS. Please try again");
		}*/
		return new ResponseMessage(
				ResponseMessage.Type.success,
				"The token has been sent to the mobile.", cookieSecret);
	}
	
	public ResponseMessage verifyGuestUSerToken(Long token, String cookieSecret)
	{
		//Decode the token value
		String guestUserMobile = null;
		if(!cookieSecret.equals("CookieNotFound"))
		{
			guestUserMobile = SecurityUtil.decodeValue(cookieSecret);
		}
		else
		{
			return new ResponseMessage(ResponseMessage.Type.danger,
					"The token is expired. Please try again!!!");
		}
		GuestUser user;
		try {
			user = guestuserDAO.findUserByMobileNoandToken(guestUserMobile, token);
			Long expiretime = user.getTokenCreatedDate();
			Long current = System.currentTimeMillis();
			Long mins = TimeUnit.MILLISECONDS.toMinutes(current-expiretime);
			Long codExpireTime = Long.valueOf(PropertiesUtil.getProperty("cod.token.expire"));
			if(mins > codExpireTime)
			{
				return new ResponseMessage(ResponseMessage.Type.danger,
						"The token is expired. Please try again!!!");
			}
			else
			{
				if(!confirmUserisRegisterdwithMobileNo(guestUserMobile))
				{
					createGuestUserwithMobileNo(guestUserMobile);
				}
				return new ResponseMessage(
						ResponseMessage.Type.success, "The token is valid");
			}
		} catch (DBRecordNotFoundException e) {
			return new ResponseMessage(ResponseMessage.Type.danger,
					"The token is incorrect.");
		}
		catch(UserServiceException e) {
			return new ResponseMessage(ResponseMessage.Type.danger,
					"There was a technical error while registering. Please try again");
		}
	}
	
	public void createGuestUserwithEmail(String email) throws UserServiceException
	{
		try {
			UserMaster user = new UserMaster();
			user.setEmail(email);
			user.setVerified(ApplicationConstants.EMAIL_VERIFIED_YES);
			user.setActive(ApplicationConstants.USER_DEACTIVATED);
			Roles assignRole = userService.findRoleByName(USER_ROLES.ROLE_GUEST.toString());
			List<Roles> listOfRoles = new ArrayList<Roles>();
			listOfRoles.add(assignRole);
			user.setListOfRoles(listOfRoles);
			userService.saveUser(user);
		} catch (RoleNotFoundException e) {
			LOG.error("Assigned Role doesnot exist");
			throw new UserServiceException(e);
		} catch (UserServiceException e) {
			LOG.error("There was a technical error while registering", e);
			throw new UserServiceException(e);
		}
	}
	
	public void createGuestUserwithMobileNo(String mobileNo) throws UserServiceException
	{
		try {
			UserMaster user = new UserMaster();
			user.setMobileNumber(mobileNo);
			user.setVerified(ApplicationConstants.MOBILENO_VERIFIED_YES);
			user.setActive(ApplicationConstants.USER_DEACTIVATED);
			Roles assignRole = userService.findRoleByName(USER_ROLES.ROLE_GUEST.toString());
			List<Roles> listOfRoles = new ArrayList<Roles>();
			listOfRoles.add(assignRole);
			user.setListOfRoles(listOfRoles);
			userService.saveUser(user);
		} catch (RoleNotFoundException e) {
			LOG.error("Assigned Role doesnot exist");
			throw new UserServiceException(e);
		} catch (UserServiceException e) {
			LOG.error("There was a technical error while registering", e);
			throw new UserServiceException(e);
		}
	}
	
	private boolean confirmUserisRegisterdwithEmail(String guestUserEmail)
	{
		try {
			userService.findByEmail(guestUserEmail);
			return true;
		} catch (EmailNotFoundException e) {
			LOG.debug("GuestUser not Found");
			return false;
		}
	}
	
	private boolean confirmUserisRegisterdwithMobileNo(String guestUserMobileNo)
	{
		try {
			userService.findByMobileNo(guestUserMobileNo);
			return true;
		} catch (DBRecordNotFoundException e) {
			LOG.debug("GuestUser not Found");
			return false;
		}
	}
	
}
