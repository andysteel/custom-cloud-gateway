package com.gmail.andersoninfonet.authserver.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.gmail.andersoninfonet.authserver.request.AuthRequest;
import com.gmail.andersoninfonet.authserver.request.RoleRequest;
import com.gmail.andersoninfonet.authserver.response.AuthResponse;
import com.gmail.andersoninfonet.authserver.service.AuthUserDetailService;
import com.gmail.andersoninfonet.authserver.util.JwtUtil;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/access/v1")
@RequiredArgsConstructor
public class AccessController {
    
    private final AuthenticationManager authenticationManager;

    private final AuthUserDetailService authUserDetailService;

    private final JwtUtil jwtUtil;

    @PostMapping(path = "/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid final AuthRequest request) {

        try {
            final var authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            if(authentication.isAuthenticated()) {
                final var userDetails = this.authUserDetailService.loadUserByUsername(request.getUsername());
                final var jwtToken = this.jwtUtil.generateAccessToken(userDetails);
                return ResponseEntity.ok(new AuthResponse(jwtToken));
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }catch(AuthenticationException ex) {
            throw new AccessDeniedException(ex.getMessage());
        }
    }

    @PostMapping(path = "/roles")
    public ResponseEntity<AuthResponse> roles(@RequestBody @Valid final RoleRequest request) {

        final var authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null) {
            final var userDetails = (UserDetails)authentication.getPrincipal();
            final var jwtToken = this.jwtUtil.generateToken(userDetails, request);
            return ResponseEntity.ok(new AuthResponse(jwtToken));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @PostMapping(path = "/check")
    public ResponseEntity<AuthResponse> check(final HttpServletRequest request) {

        final boolean isChecked = SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
        if(isChecked) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
}
