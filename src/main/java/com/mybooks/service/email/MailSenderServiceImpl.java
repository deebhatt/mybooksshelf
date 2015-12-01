package com.mybooks.service.email;

import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.mybooks.exception.EmailSenderServiceException;
import com.mybooks.utility.PropertiesUtil;

public class MailSenderServiceImpl implements EmailSenderService{
	
	private static final Log LOG = LogFactory
			.getLog(MailSenderServiceImpl.class);
	
	@Inject
	private JavaMailSender mailSender;

	public void sendEmail(SimpleMailMessage message) throws EmailSenderServiceException {
		try {
			LOG.info("About to send email to " + message.getTo()[0]);
			message.setFrom(PropertiesUtil.getProperty("email.sender.from"));
			mailSender.send(message);
			LOG.info("Email successfully sent");
		} catch (Exception e) {
			LOG.error("Error while sending email", e);
			throw new EmailSenderServiceException(e);
		}
	}

}
