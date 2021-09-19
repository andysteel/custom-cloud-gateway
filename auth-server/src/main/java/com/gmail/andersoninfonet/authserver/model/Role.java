package com.gmail.andersoninfonet.authserver.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gmail.andersoninfonet.authserver.model.enums.EnableEnum;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ROLE", schema = "AUTH_SCHEMA")
@NoArgsConstructor
@Data
public class Role implements Serializable {

    private static final long serialVersionUID = 3364890340566556267L;
    
    @Id
    @Column(name = "ID", nullable = false, length = 10)
    @SequenceGenerator(name = "SEQ_ROLE", sequenceName = "SEQ_ROLE", schema = "AUTH_SCHEMA", allocationSize = 1)
    @GeneratedValue(generator = "SEQ_ROLE", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "NAME", nullable = false, length = 200)
    private String name;

    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "DESCRIPTION", nullable = false, length = 200)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "IS_ENABLE", columnDefinition = "char", nullable = false)
    private EnableEnum isEnable;
}
