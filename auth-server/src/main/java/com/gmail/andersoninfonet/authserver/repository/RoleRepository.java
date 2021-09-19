package com.gmail.andersoninfonet.authserver.repository;

import com.gmail.andersoninfonet.authserver.model.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    

}
