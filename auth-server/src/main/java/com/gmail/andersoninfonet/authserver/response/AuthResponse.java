package com.gmail.andersoninfonet.authserver.response;

import java.io.Serializable;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AuthResponse implements Serializable {
    
    private final String jwt;
}
