package com.mybooks.utility;

import java.util.Random;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.lang3.RandomStringUtils;

public class SecurityUtil {

	/**
	 * Auto generate a random password of specified size
	 * 
	 * @param size
	 * @return
	 */
	public static String autoGeneratePassword(int size) {
		return RandomStringUtils.randomAlphanumeric(size);
	}

	/**
	 * Encode the value
	 * 
	 * @param value
	 * @return
	 */
	public static String encodeValue(String value) {
		return DatatypeConverter.printBase64Binary(value.getBytes());
	}

	/**
	 * Decode the value
	 * 
	 * @param value
	 * @return
	 */
	public static String decodeValue(String value) {
		byte[] retr = DatatypeConverter.parseBase64Binary(value);
		return new String(retr);
	}
	
	/**
	 * Returns the generated random number within the given range
	 * 
	 * @param aStart
	 * @param aEnd
	 * @return
	 */
	public static int generateRandomNumber(int aStart, int aEnd) {
		Random aRandom = new Random();
	    if (aStart > aEnd) {
	      throw new IllegalArgumentException("Start cannot exceed End.");
	    }
	    long range = (long)aEnd - (long)aStart + 1;
	    long fraction = (long)(range * aRandom.nextDouble());
	    int randomNumber =  (int)(fraction + aStart);    
	    return randomNumber;
	}

}
