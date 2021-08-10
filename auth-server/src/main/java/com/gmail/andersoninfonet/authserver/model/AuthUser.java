package com.gmail.andersoninfonet.authserver.model;

import java.io.Serializable;
import java.time.LocalDate;
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

import com.gmail.andersoninfonet.authserver.model.enums.BlockedEnum;
import com.gmail.andersoninfonet.authserver.model.enums.EnableEnum;
import com.gmail.andersoninfonet.authserver.model.enums.PassExpiredEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "USER", schema = "AUTH_SCHEMA")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
public class AuthUser implements Serializable {

    @Id
    @Column(name = "ID", nullable = false, length = 10)
    @SequenceGenerator(name = "SEQ_USER", sequenceName = "SEQ_USER", schema = "AUTH_SCHEMA", allocationSize = 1)
    @GeneratedValue(generator = "SEQ_USER", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "NAME", nullable = false, length = 200)
    private String name;

    @Column(name = "LOGIN", nullable = false, unique = true, length = 100)
    private String login;

    @Column(name = "PASSWORD", nullable = false, length = 200)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "IS_BLOCKED", columnDefinition = "char", nullable = false)
    private BlockedEnum isBlocked;

    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT", nullable = false)
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "IS_PASS_EXPIRED", columnDefinition = "char", nullable = false)
    private PassExpiredEnum isPasswordExpired;

    @Column(name = "PASS_EXPIRE_DATE", nullable = false)
    private LocalDate passwordExpiredDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "IS_ENABLE", columnDefinition = "char", nullable = false)
    private EnableEnum isEnable;
}
