package com.mybooks.exception;

import com.mybooks.enums.USER_PROFILE_ERR_CODES;

public class UserProfileValidationException extends BaseException{
	
	private USER_PROFILE_ERR_CODES errorCode;

	public UserProfileValidationException(USER_PROFILE_ERR_CODES errorCode) {
		this.errorCode = errorCode;
	}

}
