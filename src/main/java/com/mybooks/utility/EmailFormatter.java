package com.mybooks.utility;

import org.springframework.mail.SimpleMailMessage;

import com.mybooks.entities.GuestUser;
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
	
	public static SimpleMailMessage generateTokenEmailMessage(String token, GuestUser user) {
		SimpleMailMessage message = new SimpleMailMessage();
		StringBuffer mailContent = new StringBuffer();
		mailContent.append("The OTP for your cash on delivery order is ");
		mailContent.append(token);
		mailContent.append(" The OTP will be valid for 10 mins only!");
		
		message.setTo(user.getEmail());
		message.setSubject("OTP for Cash On Delivery @ MyBooksShelf");
		message.setText(mailContent.toString());
		return message;
	}

}
