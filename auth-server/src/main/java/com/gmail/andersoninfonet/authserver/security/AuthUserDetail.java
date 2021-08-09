package com.gmail.andersoninfonet.authserver.security;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.gmail.andersoninfonet.authserver.model.AuthUser;
import com.gmail.andersoninfonet.authserver.model.enums.BlockedEnum;
import com.gmail.andersoninfonet.authserver.model.enums.EnableEnum;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthUserDetail implements UserDetails {


    private String username;
    private String password;
    private List<GrantedAuthority> roles;
    private boolean active;
    private LocalDate passwordExpiredDate;
    private boolean locked;
    

    /**
     * @param username
     * */
    public AuthUserDetail(final AuthUser user) {
        this.username = user.getLogin();
        this.password = user.getPassword();
        this.active = user.getIsEnable().equals(EnableEnum.YES);
        this.passwordExpiredDate = user.getPasswordExpiredDate();
        this.locked = user.getIsBlocked().equals(BlockedEnum.YES);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
		if (roles == null) {
			roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}
		return roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.passwordExpiredDate == null || LocalDate.now().isBefore(this.passwordExpiredDate);
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.passwordExpiredDate == null || LocalDate.now().isBefore(this.passwordExpiredDate);
    }

    @Override
    public boolean isEnabled() {
        return this.active;
    }
}
