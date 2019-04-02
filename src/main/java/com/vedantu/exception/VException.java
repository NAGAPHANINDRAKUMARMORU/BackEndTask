package com.vedantu.exception;

import org.apache.logging.log4j.Level;

import com.vedantu.enums.ErrorCode;

public class VException extends Exception{

    private static final long serialVersionUID = 1L;
    private ErrorCode errorCode;
    private String errorMessage;
    // This field is only used for logging uncaught exceptions at different log
    // levels
    // This default value can be different in a subclass.
    private transient Level level = Level.INFO;

    public VException(ErrorCode errorCode, String errorMessage, Level level) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.level = level;
    }

    public VException(ErrorCode errorCode, String errorMessage) {
        this(errorCode, errorMessage, Level.INFO);
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void String(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "VException{" + "errorCode=" + errorCode + ", errorMessage=" + errorMessage + ", level=" + level + '}';
    }
}
