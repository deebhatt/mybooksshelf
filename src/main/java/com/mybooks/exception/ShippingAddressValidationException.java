package com.mybooks.exception;

import com.mybooks.enums.USER_PROFILE_ERR_CODES;

public class ShippingAddressValidationException extends BaseException{
	
	private USER_PROFILE_ERR_CODES errorCode;

	public ShippingAddressValidationException(USER_PROFILE_ERR_CODES errorCode) {
		this.errorCode = errorCode;
	}

	public USER_PROFILE_ERR_CODES getErrorCode() {
		return errorCode;
	}

}
