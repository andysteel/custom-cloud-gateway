package com.gmail.andersoninfonet.authserver.util;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmail.andersoninfonet.authserver.dto.AccessDTO;
import com.gmail.andersoninfonet.authserver.exception.AuthServerException;
import com.gmail.andersoninfonet.authserver.request.RoleRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
    
    @Value("${secret.key}")
    private String secretKey;

    private static final String ROLE_ACCESS = "ROLE_ACCESS";

    public String extractUsername(final String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(final String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(final String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(final String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    public String generateToken(final UserDetails userDetail, final RoleRequest request) {
        final Map<String, Object> mountClaims = new HashMap<>();
        final Map<String, Object> claims = new HashMap<>();
        if(userDetail.getAuthorities() != null && !userDetail.getAuthorities().isEmpty()) {
            userDetail.getAuthorities().forEach(a -> {
                final String[] authorities = a.getAuthority().split("/");
                mountClaims.put(authorities[0], authorities[1]);
            });
        }
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            final List<AccessDTO> accessList =  objectMapper.readValue((String) mountClaims.get(ROLE_ACCESS), new TypeReference<List<AccessDTO>>(){});
            if(accessList != null && !accessList.isEmpty()) {
                accessList.forEach(access -> {
                    if(access.getAppId().equals(request.getAppID()) && (access.getRole().equals(request.getRole()))) {
                        claims.put(ROLE_ACCESS, access);
                    }
                });
            }

        } catch(JsonProcessingException ex) {
            throw new AuthServerException(ex.getMessage());
        }

        return this.createToken(claims, userDetail);
    }

    private String createToken(final Map<String, Object> claims, final UserDetails userDetail) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetail.getUsername())
                .setIssuedAt(new Date())
                //expiration in 2 hours
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 2))
                .signWith(SignatureAlgorithm.HS256, secretKey).compact();
    }

    public String generateAccessToken(final UserDetails userDetail) {
        final Map<String, Object> claims = new HashMap<>();
        return this.createAccessToken(claims, userDetail.getUsername());
    }

    private String createAccessToken(final Map<String, Object> claims, final String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, secretKey).compact();
    }

    public boolean validateToken(final String token, final UserDetails userDetail) {
        final String username = this.extractUsername(token);
        return (username.equals(userDetail.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(final String token) {
        return this.extractExpiration(token).before(new Date());
    }
}
