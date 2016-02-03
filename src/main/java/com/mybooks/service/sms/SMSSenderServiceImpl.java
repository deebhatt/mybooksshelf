package com.mybooks.service.sms;

import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.mybooks.exception.SmsSenderServiceException;

@Service("smssenderServiceImpl")
public class SMSSenderServiceImpl implements SMSSenderService{
	
	private static final Log LOG = LogFactory
			.getLog(SMSSenderServiceImpl.class);
	
	@Inject
	private SMSLeadsSenderImpl smsSender;

	@Override
	public void sendSMS(SMSLeadsMessage message) throws SmsSenderServiceException {
		try {
			LOG.info("About to send SMS to " + message.getNumbers());
			smsSender.sendSMS(message);
			LOG.info("SMS successfully sent");
		} catch (Exception e) {
			LOG.error("Error while sending SMS", e);
			throw new SmsSenderServiceException(e);
		}
	}

}
