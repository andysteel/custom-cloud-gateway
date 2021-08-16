package com.gmail.andersoninfonet.authserver.request;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleRequest implements Serializable {
    
    @NotBlank(message = "appID must be informed")
    private String appID;

    @NotBlank(message = "role must be informed")
    private String role;
}
