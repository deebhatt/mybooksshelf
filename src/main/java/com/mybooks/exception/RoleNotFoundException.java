package com.mybooks.exception;

public class RoleNotFoundException extends BaseException {
	
	public RoleNotFoundException(Exception e) {
		super(e);
	}

	public RoleNotFoundException() {
		super();
	}

}
