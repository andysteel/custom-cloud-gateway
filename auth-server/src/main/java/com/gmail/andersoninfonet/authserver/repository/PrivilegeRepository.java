package com.gmail.andersoninfonet.authserver.repository;

import com.gmail.andersoninfonet.authserver.model.Privilege;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
    
}
