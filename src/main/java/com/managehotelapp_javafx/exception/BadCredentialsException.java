package com.managehotelapp_javafx.exception;

public class BadCredentialsException extends RuntimeException{

    public BadCredentialsException(String errMessage){
        super(errMessage);
    }
}
