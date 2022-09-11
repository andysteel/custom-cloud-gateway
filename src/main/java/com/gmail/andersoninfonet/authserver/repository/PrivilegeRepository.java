package com.gmail.andersoninfonet.authserver.repository;

import java.util.List;

import com.gmail.andersoninfonet.authserver.model.Privilege;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
    

    @Query(value = "select pl from Privilege pl where pl.role.id=:roleId")
    List<Privilege> findByRole(@Param("roleId") final Long roleId);
}
