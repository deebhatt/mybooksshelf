package com.mybooks.exception;

public class EmailNotFoundException extends BaseException{

	public EmailNotFoundException(Exception e) {
		super(e);
	}

	public EmailNotFoundException() {
		super();
	}

}
