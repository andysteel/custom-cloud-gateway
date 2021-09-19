package com.gmail.andersoninfonet.authserver.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gmail.andersoninfonet.authserver.model.enums.EnableEnum;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PRIVILEGE", schema = "AUTH_SCHEMA")
@NoArgsConstructor
@Data
public class Privilege implements Serializable {

    private static final long serialVersionUID = 2749788727244286560L;
    
    @Id
    @Column(name = "ID", nullable = false, length = 10)
    @SequenceGenerator(name = "SEQ_PRIVILEGE", sequenceName = "SEQ_PRIVILEGE", schema = "AUTH_SCHEMA", allocationSize = 1)
    @GeneratedValue(generator = "SEQ_PRIVILEGE", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "NAME", nullable = false, length = 200)
    private String name;

    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT", nullable = false)
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "IS_ENABLE", columnDefinition = "char", nullable = false)
    private EnableEnum isEnable;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")
    private Role role;
}
