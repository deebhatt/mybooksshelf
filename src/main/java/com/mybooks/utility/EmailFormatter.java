package com.mybooks.utility;

import org.springframework.mail.SimpleMailMessage;

import com.mybooks.entities.UserMaster;

public class EmailFormatter {
	
	public static SimpleMailMessage resetPasswordMessage(UserMaster user, String password) {
		SimpleMailMessage message = new SimpleMailMessage();
		StringBuffer mailContent = new StringBuffer();
		mailContent.append("Your new password is ");

		mailContent.append(password);

		message.setTo(user.getEmail());
		message.setSubject("Password Reset Notification");
		message.setText(mailContent.toString());

		return message;
	}

}
