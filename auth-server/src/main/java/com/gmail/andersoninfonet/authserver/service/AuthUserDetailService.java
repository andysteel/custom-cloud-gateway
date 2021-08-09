package com.gmail.andersoninfonet.authserver.service;

import com.gmail.andersoninfonet.authserver.exception.UserNotFoundException;
import com.gmail.andersoninfonet.authserver.model.AuthUser;
import com.gmail.andersoninfonet.authserver.repository.AuthUserRepository;
import com.gmail.andersoninfonet.authserver.security.AuthUserDetail;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.Data;

@Data
@Service
public class AuthUserDetailService implements UserDetailsService {

    private final AuthUserRepository authUserRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final AuthUser user = this.authUserRepository.findByLogin(username).orElseThrow(() -> new UserNotFoundException("User not found."));
        return new AuthUserDetail(user);
    }
}
