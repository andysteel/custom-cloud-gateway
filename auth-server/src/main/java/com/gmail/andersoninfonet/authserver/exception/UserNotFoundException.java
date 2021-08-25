package com.gmail.andersoninfonet.authserver.exception;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = UNAUTHORIZED)
public class UserNotFoundException extends RuntimeException {

    /**
     * 
     */
    public UserNotFoundException() {
    }

    /**
     * @param message
     */
    public UserNotFoundException(String message) {
        super(message);
    }
    
    
}
