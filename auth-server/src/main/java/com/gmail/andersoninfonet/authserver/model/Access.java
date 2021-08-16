package com.gmail.andersoninfonet.authserver.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.gmail.andersoninfonet.authserver.model.keys.AccessPK;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ACCESS", schema = "AUTH_SCHEMA")
@NoArgsConstructor
@Data
public class Access implements Serializable {

    private static final long serialVersionUID = -991225309318934616L;

    @EmbeddedId
    private AccessPK accessPK;

    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT", nullable = false)
    private LocalDateTime updatedAt;
}
