package com.capps.maskav2.web.rest.excption;

public class IncorrectPasswordException extends RuntimeException {
    public IncorrectPasswordException(String incorrectPassword) {
        super(incorrectPassword);
    }
}
