package com.giri.springbootrestfulwebservices.exception;

public class EmailAlreadyExsitingException extends RuntimeException{
    private String message;

    public EmailAlreadyExsitingException(String message){
        super(message);
    }
}
