package com.gmail.andersoninfonet.authserver.model.keys;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.gmail.andersoninfonet.authserver.model.Application;
import com.gmail.andersoninfonet.authserver.model.AuthUser;
import com.gmail.andersoninfonet.authserver.model.Role;

import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@Data
public class AccessPK implements Serializable {

    private static final long serialVersionUID = -7261887879839337877L;
    
    @Column(name = "ID", nullable = false, length = 10)
    @SequenceGenerator(name = "SEQ_ACCESS", sequenceName = "SEQ_ACCESS", schema = "AUTH_SCHEMA", allocationSize = 1)
    @GeneratedValue(generator = "SEQ_ACCESS", strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "APP_ID", referencedColumnName = "ID")
    private Application application;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private AuthUser user;
}
