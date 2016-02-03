package com.mybooks.service.sms;

import com.mybooks.exception.SmsSenderServiceException;

public interface SMSSenderService {
	
	public void sendSMS(SMSLeadsMessage message)
			throws SmsSenderServiceException;

}
