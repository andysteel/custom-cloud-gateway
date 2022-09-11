package com.gmail.andersoninfonet.authserver.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class RoleDTO {

    private String name;
    List<String> privileges = new ArrayList<>();
}
