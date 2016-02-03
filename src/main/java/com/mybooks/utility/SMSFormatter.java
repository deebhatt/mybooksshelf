package com.mybooks.utility;

import com.mybooks.entities.GuestUser;
import com.mybooks.service.sms.SMSLeadsMessage;

public class SMSFormatter {
	
	public static SMSLeadsMessage generateTokenforCashOnDelivery(String token, GuestUser user)
	{
		SMSLeadsMessage message = new SMSLeadsMessage();
		StringBuffer smsContent = new StringBuffer();
		smsContent.append("The OTP for your cash on delivery order is ");
		smsContent.append(token);
		smsContent.append(" The OTP will be valid for 10 mins only!");
		message.setMessage(smsContent.toString());
		message.setNumbers(user.getMobileNumber());
		return message;
	}

}
