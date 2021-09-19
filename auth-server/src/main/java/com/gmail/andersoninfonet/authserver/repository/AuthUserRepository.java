package com.gmail.andersoninfonet.authserver.repository;

import java.util.Optional;

import com.gmail.andersoninfonet.authserver.model.AuthUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {
    
    Optional<AuthUser> findByLogin(final String login);
}
