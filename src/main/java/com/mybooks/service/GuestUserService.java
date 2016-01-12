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
import com.mybooks.exception.UserServiceException;
import com.mybooks.service.email.EmailSenderService;
import com.mybooks.utility.EmailFormatter;
import com.mybooks.utility.PropertiesUtil;
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
	private UserService userService;
	
	@Transactional(rollbackFor=DAOException.class)
	public ResponseMessage sendVerificationTokentoUser(String email)
	{
		GuestUser guestuser = null;
		int generateEmailToken = 0;
		try {
			int startValue = Integer.parseInt(PropertiesUtil.getProperty("email.token.start"));
			int endValue = Integer.parseInt(PropertiesUtil.getProperty("email.token.end"));
			generateEmailToken = SecurityUtil.generateRandomNumber(startValue, endValue);
			
			guestuser = guestuserDAO.findUserByEmail(email);
			//User already found so update the user
			guestuser.setTokenCreatedDate(System.currentTimeMillis());
			guestuser.setToken((long) generateEmailToken);
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
				guestuser.setEmail(email);
				guestuser.setToken((long) generateEmailToken);
				guestuser.setTokenCreatedDate(System.currentTimeMillis());
				guestuserDAO.persist(guestuser);
			}
		} catch (DAOException e) {
			LOG.error("There was a technical error while adding Guest User", e);
			return new ResponseMessage(ResponseMessage.Type.danger,
					"There was a technical error while adding Guest User. Please try again");
		}
		// Encode Email and currentmillis value
		String cookieSecret = SecurityUtil.encodeValue(email);
					
		// SEND EMAIL NOTIFICATION
		/*SimpleMailMessage message = EmailFormatter.generateTokenEmailMessage(String.valueOf(generateEmailToken), guestuser);
		try {
			emailSender.sendEmail(message);
		} catch (EmailSenderServiceException e) {
			LOG.warn("Errors while processing reset password", e);
		}*/
		return new ResponseMessage(
				ResponseMessage.Type.success,
				"The token has been sent to the email.", cookieSecret);
	}
	
	public ResponseMessage verifyGuestUSerToken(Long token, String cookieSecret)
	{
		//Decode the token value
		String guestUserEmail = null;
		if(!cookieSecret.equals("CookieNotFound"))
		{
			guestUserEmail = SecurityUtil.decodeValue(cookieSecret);
		}
		else
		{
			return new ResponseMessage(ResponseMessage.Type.danger,
					"The token is expired. Please try again!!!");
		}
		GuestUser user;
		try {
			user = guestuserDAO.findUserByEmailandToken(guestUserEmail, token);
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
				if(!confirmUserisRegisterd(guestUserEmail))
				{
					createGuestUser(guestUserEmail);
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
	
	public void createGuestUser(String email) throws UserServiceException
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
	
	private boolean confirmUserisRegisterd(String guestUserEmail)
	{
		try {
			userService.findByEmail(guestUserEmail);
			return true;
		} catch (EmailNotFoundException e) {
			LOG.debug("GuestUser not Found");
			return false;
		}
	}
	
}
