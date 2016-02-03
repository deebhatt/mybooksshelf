package com.mybooks.service.sms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mybooks.exception.SmsSenderServiceException;

public class SMSLeadsSenderImpl {
	
	private static final Log LOG = LogFactory
			.getLog(SMSLeadsSenderImpl.class);
	
	private final String DEFAULT_URL = "http://smsleads.in/pushsms.php";
	
	private String userName;
	
	private String password;
	
	private String senderid;
	
	public SMSLeadsSenderImpl() {
	}
	
	public void sendSMS(SMSLeadsMessage message) throws SmsSenderServiceException
	{
		try {
			String urlEncodedMessage = URLEncoder.encode(message.getMessage(), "UTF-8");
			String numbers = message.getNumbers();
			
			StringBuffer url = new StringBuffer();
			url.append(DEFAULT_URL);
			url.append("?username="+getUserName());
			url.append("&password="+getPassword());
			url.append("&message="+urlEncodedMessage);
			url.append("&sender="+getSenderid());
			url.append("&numbers="+numbers);
			
			URL postUrl = new URL(url.toString());
			HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();
			
			BufferedReader reader = new BufferedReader(new
		              InputStreamReader(connection.getInputStream()));

		      String line = reader.readLine();
		      while (line != null)
		      {
		         LOG.debug("Response from SMSLeads : "+line);
		         line = reader.readLine();
		      }
		      
		      connection.disconnect();
			
		} catch (UnsupportedEncodingException e) {
			LOG.error("Message to this number : "+message.getNumbers()+" failed.");
			throw new SmsSenderServiceException(e);
		} catch(MalformedURLException e) {
			LOG.error("Message to this number : "+message.getNumbers()+" failed.");
			throw new SmsSenderServiceException(e);
		} catch (IOException e) {
			LOG.error("Message to this number : "+message.getNumbers()+" failed.");
			throw new SmsSenderServiceException(e);
		}
		
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSenderid() {
		return senderid;
	}

	public void setSenderid(String senderid) {
		this.senderid = senderid;
	}

}
