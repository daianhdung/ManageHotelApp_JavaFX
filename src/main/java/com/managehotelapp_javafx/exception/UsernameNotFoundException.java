package com.managehotelapp_javafx.exception;

public class UsernameNotFoundException extends RuntimeException{

    public UsernameNotFoundException(String errMessage){
        super(errMessage);
    }
}
