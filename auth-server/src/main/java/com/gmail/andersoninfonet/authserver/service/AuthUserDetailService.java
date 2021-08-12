package com.gmail.andersoninfonet.authserver.service;

import java.util.List;

import com.gmail.andersoninfonet.authserver.exception.UserNotFoundException;
import com.gmail.andersoninfonet.authserver.model.Access;
import com.gmail.andersoninfonet.authserver.model.AuthUser;
import com.gmail.andersoninfonet.authserver.repository.AccessRepository;
import com.gmail.andersoninfonet.authserver.repository.AuthUserRepository;
import com.gmail.andersoninfonet.authserver.security.AuthUserDetail;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.Data;

@Data
@Service
@Transactional
public class AuthUserDetailService implements UserDetailsService {

    private final AuthUserRepository authUserRepository;

    private final AccessRepository accessRepository;
    
    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final AuthUser user = this.authUserRepository.findByLogin(username).orElseThrow(() -> new UserNotFoundException("User not found."));
        final List<Access> accesses = this.accessRepository.findAllByUser(user);
        return new AuthUserDetail(user, accesses);
    }
}
