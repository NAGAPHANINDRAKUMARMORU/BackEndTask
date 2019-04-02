package com.vedantu.exception;

import org.apache.logging.log4j.Level;

import com.vedantu.enums.ErrorCode;

public class BadRequestException extends VException {
	
	 private static final long serialVersionUID = 1L;

	    public BadRequestException(ErrorCode errorCode, String errorMessage) {
	        super(errorCode, errorMessage);
	    }

	    public BadRequestException(ErrorCode errorCode, String errorMessage, Level level) {
	        super(errorCode, errorMessage, level);
	    }

}
