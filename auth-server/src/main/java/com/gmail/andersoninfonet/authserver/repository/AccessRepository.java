package com.gmail.andersoninfonet.authserver.repository;

import java.util.List;

import com.gmail.andersoninfonet.authserver.model.Access;
import com.gmail.andersoninfonet.authserver.model.AuthUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessRepository extends JpaRepository<Access, Long> {
    
    List<Access> findAllByUser(final AuthUser user);
}
