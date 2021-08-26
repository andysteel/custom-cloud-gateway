package com.gmail.andersoninfonet.authserver.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class AccessDTO {
    
    private String appId;
    private String role;
    private List<String> privileges = new ArrayList<>();
}
