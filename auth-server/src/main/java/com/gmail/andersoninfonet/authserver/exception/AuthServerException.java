package com.gmail.andersoninfonet.authserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class AuthServerException extends RuntimeException {
    
    /**
     * 
     */
    public AuthServerException() {
    }

    /**
     * @param message
     */
    public AuthServerException(String message) {
        super(message);
    }
}
