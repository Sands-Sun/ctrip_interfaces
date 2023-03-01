package com.dne.core.common;

public class BaseException extends RuntimeException{

    protected int errorCode;
    protected String errorMessage;


    public BaseException() {
        super();
    }
    public BaseException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public BaseException(int errorCode, String errorMessage){
        super(String.valueOf(errorCode));
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public BaseException(int errorCode, String errorMessage, Throwable cause){
        super(String.valueOf(errorCode), cause);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
