package com.mybooks.validator;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.mybooks.enums.USER_PROFILE_ERR_CODES;
import com.mybooks.enums.VALIDATION_MODE;
import com.mybooks.exception.ShippingAddressValidationException;
import com.mybooks.mbeans.AddressBean;

@Repository
@Service("shippingaddressValidator")
public class ShippingAddressValidator {
	
	private static final Log LOG = LogFactory.getLog(ShippingAddressValidator.class);
	
	public void isShippingAddressValid(AddressBean shippingAddressBean, VALIDATION_MODE mode) throws ShippingAddressValidationException
	{
		switch (mode) {
		case SAVE:
			validateSave(shippingAddressBean);
			break;
		case UPDATE:
			break;
		default:
			break;
		}
	}

	private void validateSave(AddressBean shippingAddressBean)throws ShippingAddressValidationException
	{
		validateFullName(shippingAddressBean);
		validateAddressLine1(shippingAddressBean);
		validateAddressLine2(shippingAddressBean);
		validateTownorCity(shippingAddressBean);
		validateState(shippingAddressBean);
		validatePinCode(shippingAddressBean);
		validateMobileNumber(shippingAddressBean);
		validateLandMark(shippingAddressBean);
		validateAddressType(shippingAddressBean);
	}

	private void validateAddressType(AddressBean shippingAddressBean)
			throws ShippingAddressValidationException {
		if (StringUtils.isEmpty(shippingAddressBean.getAddressType())) {
			LOG.debug("AddressType is Required");
			throw new ShippingAddressValidationException(
					USER_PROFILE_ERR_CODES.VALUE_REQUIRED);
		}
	}

	private void validateLandMark(AddressBean shippingAddressBean)
			throws ShippingAddressValidationException {
		if (StringUtils.isEmpty(shippingAddressBean.getLandMark())) {
			LOG.debug("LandMark is Required");
			throw new ShippingAddressValidationException(
					USER_PROFILE_ERR_CODES.VALUE_REQUIRED);
		}
	}

	private void validateMobileNumber(AddressBean shippingAddressBean)
			throws ShippingAddressValidationException {
		if (StringUtils.isEmpty(shippingAddressBean.getMobileNumber())) {
			LOG.debug("MobileNumber Required");
			throw new ShippingAddressValidationException(
					USER_PROFILE_ERR_CODES.VALUE_REQUIRED);
		}
	}

	private void validatePinCode(AddressBean shippingAddressBean)
			throws ShippingAddressValidationException {
		if (StringUtils.isEmpty(String.valueOf(shippingAddressBean.getPinCode()))) {
			LOG.debug("PinCode Required");
			throw new ShippingAddressValidationException(
					USER_PROFILE_ERR_CODES.VALUE_REQUIRED);
		}
	}

	private void validateState(AddressBean shippingAddressBean)
			throws ShippingAddressValidationException {
		if (StringUtils.isEmpty(shippingAddressBean.getState())) {
			LOG.debug("State value Required");
			throw new ShippingAddressValidationException(
					USER_PROFILE_ERR_CODES.VALUE_REQUIRED);
		}
	}

	private void validateTownorCity(AddressBean shippingAddressBean)
			throws ShippingAddressValidationException {
		if (StringUtils.isEmpty(shippingAddressBean.getTownOrCity())) {
			LOG.debug("TownorCity Required");
			throw new ShippingAddressValidationException(
					USER_PROFILE_ERR_CODES.VALUE_REQUIRED);
		}
	}

	private void validateAddressLine2(AddressBean shippingAddressBean)
			throws ShippingAddressValidationException {
		if (StringUtils.isEmpty(shippingAddressBean.getAddressLine2())) {
			LOG.debug("AddressLine2 Required");
			throw new ShippingAddressValidationException(
					USER_PROFILE_ERR_CODES.VALUE_REQUIRED);
		}
	}

	private void validateAddressLine1(AddressBean shippingAddressBean)
			throws ShippingAddressValidationException {
		if (StringUtils.isEmpty(shippingAddressBean.getAddressLine1())) {
			LOG.debug("AddressLine1 Required");
			throw new ShippingAddressValidationException(
					USER_PROFILE_ERR_CODES.VALUE_REQUIRED);
		}
	}

	private void validateFullName(AddressBean shippingAddressBean)
			throws ShippingAddressValidationException {
		if (StringUtils.isEmpty(shippingAddressBean.getFullName())) {
			LOG.debug("Full Name Required");
			throw new ShippingAddressValidationException(
					USER_PROFILE_ERR_CODES.VALUE_REQUIRED);
		}
	}
}
