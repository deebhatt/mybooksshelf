package com.mybooks.service.email;

import org.springframework.mail.SimpleMailMessage;

import com.mybooks.exception.EmailSenderServiceException;

public interface EmailSenderService {

	public void sendEmail(SimpleMailMessage message)
			throws EmailSenderServiceException;
}
