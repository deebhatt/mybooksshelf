package com.mybooks.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mybooks.commons.ResponseMessage;
import com.mybooks.constants.ApplicationConstants;
import com.mybooks.entities.Roles;
import com.mybooks.entities.UserMaster;
import com.mybooks.enums.USER_ROLES;
import com.mybooks.enums.VALIDATION_MODE;
import com.mybooks.exception.DAOException;
import com.mybooks.exception.UserProfileValidationException;
import com.mybooks.exception.UserServiceException;
import com.mybooks.mbeans.UserFormBean;
import com.mybooks.service.email.EmailSenderService;
import com.mybooks.utility.EmailFormatter;
import com.mybooks.utility.SecurityUtil;
import com.mybooks.validator.UserValidator;

@Service("registrationService")
@Repository
public class RegistrationService {
	
	private static final Log LOG = LogFactory.getLog(RegistrationService.class);
	
	@Inject
	private UserService userService;
	
	@Inject
	private UserValidator userValidator;
	
	@Inject
	private PasswordEncoder passwordEncoder;
	
	@Inject
	private EmailSenderService emailSender;

	@Transactional(rollbackFor = DAOException.class)
	public ResponseMessage registerUser(UserFormBean userFormBean) {
		try	{
			//Validate User Master
			userValidator.isUserMasterDataValid(userFormBean,
					VALIDATION_MODE.SAVE, null);
			UserMaster userMaster = new UserMaster();
			userMaster.setFirstName(userFormBean.getFirstName());
			userMaster.setLastName(userFormBean.getLastName());
			userMaster.setEmail(userFormBean.getEmail());
			userMaster.setPassword(passwordEncoder.encode(userFormBean
					.getPassword()));
			userMaster.setVerified(ApplicationConstants.EMAIL_VERIFIED_NO);
			userMaster.setActive(ApplicationConstants.USER_ACTIVATED);
			
			userMaster.setListOfRoles(userFormBean.getRoles());
			userService.saveUser(userMaster);
			
			return new ResponseMessage(
					ResponseMessage.Type.success,
					"You have been successfully registered", Long.toString(userMaster.getUserId()));
		}	catch(UserProfileValidationException e)	{
			LOG.error("There was a validation error while registering", e);
			return new ResponseMessage(ResponseMessage.Type.danger,
					e.getErrorCode().toString());
		} catch (UserServiceException e) {
			LOG.error("There was a technical error while registering", e);
			return new ResponseMessage(ResponseMessage.Type.danger,
					"There was a technical error while registering. Please try again");
		}
	}
	
	public ResponseMessage resetPassword(UserFormBean userFormBean) {
		UserMaster user;
		try {
			user = userService.findByEmail(userFormBean.getEmail());
			// Autogenerate password
			String passwordValue = SecurityUtil.autoGeneratePassword(12);
			user.setPassword(passwordEncoder.encode(passwordValue));
			userService.updateUser(user);

			// SEND PASSWORD CHANGE EMAIL NOTIFICATION
			SimpleMailMessage message = EmailFormatter.resetPasswordMessage(
					user, passwordValue);

			emailSender.sendEmail(message);

		} catch (Exception e) {
			// Dont throw any errors to the screen to prevent account harvesting
			LOG.warn("Errors while processing reset password", e);
		}
		return new ResponseMessage(
				ResponseMessage.Type.success,
				"The new password has been sent to your registered email. Please log into the application using it.");
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseMessage changePassword(UserFormBean userFormBean) {

		// Get logged in user
		try {
			UserMaster user = userService.getLoggedInUser();

			userValidator.isUserMasterDataValid(userFormBean,
					VALIDATION_MODE.CHANGE_PASSWORD, user.getPassword());

			String newPassword = userFormBean.getPassword();
			user.setPassword(passwordEncoder.encode(newPassword));
			user.setActive(ApplicationConstants.USER_ACTIVATED);
			userService.updateUser(user);

		} catch (UserProfileValidationException e) {
			// TODO: Implement error message from exception
			return new ResponseMessage(ResponseMessage.Type.danger,
					"There was some validation errors. Please try again!!!");
		} catch (Exception e) {
			LOG.error("Error while changing pasword ", e);
			return new ResponseMessage(ResponseMessage.Type.danger,
					"Unable to change your password. Please try again!!!");
		}
		return new ResponseMessage(
				ResponseMessage.Type.success,
				"The new password has been sent to your registered email. Please log into the application using it.");
	}

}
