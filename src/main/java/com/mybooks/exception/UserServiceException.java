package com.mybooks.exception;

public class UserServiceException extends ServiceException{

	public UserServiceException(Exception e) {
		super(e);
	}

	public UserServiceException() {
		super();
	}
}
