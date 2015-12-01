package com.mybooks.exception;

public class DBRecordNotFoundException extends DAOException{
	
	public DBRecordNotFoundException(Exception e) {
		super(e);
	}

}
