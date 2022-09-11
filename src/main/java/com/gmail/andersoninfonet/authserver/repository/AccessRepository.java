package com.gmail.andersoninfonet.authserver.repository;

import java.util.List;

import com.gmail.andersoninfonet.authserver.model.Access;
import com.gmail.andersoninfonet.authserver.model.keys.AccessPK;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessRepository extends JpaRepository<Access, AccessPK> {
    
    @Query(value = "select acs from Access acs where acs.accessPK.user.id=:userId")
    List<Access> findAllByUser(@Param("userId") final Long userId);
}
