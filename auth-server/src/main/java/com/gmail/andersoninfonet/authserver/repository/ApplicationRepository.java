package com.gmail.andersoninfonet.authserver.repository;

import com.gmail.andersoninfonet.authserver.model.Application;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    
}
