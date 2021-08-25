package com.gmail.andersoninfonet.authserver.request;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthRequest implements Serializable {
    
    @NotBlank(message = "Username must be informed")
    private String username;

    @NotBlank(message = "Password must be informed")
    private String password;
}
