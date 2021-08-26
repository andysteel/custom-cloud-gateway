package com.gmail.andersoninfonet.authserver.dto;

import java.io.Serializable;
import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionDTO implements Serializable {
    
    private String message;
    private int status;
    private String className;
    private Instant timestamp;
}
