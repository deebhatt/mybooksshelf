package com.mybooks.exception;

public class OrderServiceException extends ServiceException{
	
	public OrderServiceException(Exception e) {
		super(e);
	}

	public OrderServiceException() {
		super();
	}

}
