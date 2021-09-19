package com.gmail.andersoninfonet.authserver.security;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.gmail.andersoninfonet.authserver.model.AuthUser;
import com.gmail.andersoninfonet.authserver.model.enums.BlockedEnum;
import com.gmail.andersoninfonet.authserver.model.enums.EnableEnum;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthUserDetail implements UserDetails {


    private String username;
    private String password;
    private Set<GrantedAuthority> roles;
    private boolean active;
    private LocalDate passwordExpiredDate;
    private boolean nonLocked;
    

    /**
     * @param username
     * */
    public AuthUserDetail(final AuthUser user,  final Set<Map<String, Object>> accesses) {
        this.username = user.getLogin();
        this.password = user.getPassword();
        this.active = user.getIsEnable().equals(EnableEnum.Y);
        this.passwordExpiredDate = user.getPasswordExpiredDate();
        this.nonLocked = user.getIsBlocked().equals(BlockedEnum.N);
        if(accesses != null && !accesses.isEmpty()) {
            roles = new HashSet<>();
            roles.add(new SimpleGrantedAuthority("ROLE_ACCESS/"+accesses.toString().replace("=", ":")));
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
		if (roles == null) {
			roles = Collections.emptySet();
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
        return this.nonLocked;
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
