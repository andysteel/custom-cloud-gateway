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
@Table(name = "APPLICATION", schema = "AUTH_SCHEMA")
@NoArgsConstructor
@Data
public class Application implements Serializable {

    private static final long serialVersionUID = -3209755581046882279L;
    
    @Id
    @Column(name = "ID", nullable = false, length = 10)
    @SequenceGenerator(name = "SEQ_APPLICATION", sequenceName = "SEQ_APPLICATION", schema = "AUTH_SCHEMA", allocationSize = 1)
    @GeneratedValue(generator = "SEQ_APPLICATION", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "NAME", nullable = false, length = 200)
    private String name;

    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "DESCRIPTION", nullable = false, length = 200)
    private String description;

    @Column(name = "APP_ID", nullable = false, length = 200)
    private String appID;

    @Column(name = "APP_SECRET", nullable = false, length = 200)
    private String appSecret;

    @Column(name = "URL", nullable = false, length = 200)
    private String url;

    @Column(name = "WEB_CONTEXT", nullable = false, length = 200)
    private String webContext;

    @Column(name = "PORT", nullable = false, length = 5)
    private Integer port;

    @Column(name = "URI", nullable = false, length = 1000)
    private String uri;

    @Enumerated(EnumType.STRING)
    @Column(name = "IS_ENABLE", columnDefinition = "char", nullable = false)
    private EnableEnum isEnable;
}
