package com.gmail.andersoninfonet.authserver.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.gmail.andersoninfonet.authserver.dto.RoleDTO;
import com.gmail.andersoninfonet.authserver.exception.UserNotFoundException;
import com.gmail.andersoninfonet.authserver.model.Access;
import com.gmail.andersoninfonet.authserver.model.AuthUser;
import com.gmail.andersoninfonet.authserver.model.Privilege;
import com.gmail.andersoninfonet.authserver.repository.AccessRepository;
import com.gmail.andersoninfonet.authserver.repository.AuthUserRepository;
import com.gmail.andersoninfonet.authserver.repository.PrivilegeRepository;
import com.gmail.andersoninfonet.authserver.security.AuthUserDetail;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.Data;

@Data
@Service
@Transactional
public class AuthUserDetailService implements UserDetailsService {

    private final AuthUserRepository authUserRepository;

    private final AccessRepository accessRepository;

    private final PrivilegeRepository privilegeRepository;

    private static final String APP_ID = "appId";

    private static final String ROLE = "role";

    private static final String PRIVILEGES = "privileges";
    
    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final AuthUser user = this.authUserRepository.findByLogin(username).orElseThrow(() -> new UserNotFoundException("User not found."));
        final List<Access> accesses = this.accessRepository.findAllByUser(user.getId());
        final Set<Map<String, Object>> roles = new HashSet<>();

        this.mountRoles(accesses, roles);
        return new AuthUserDetail(user, roles);
    }

    private void mountRoles(final List<Access> accesses, final Set<Map<String, Object>> roles) {
        accesses.forEach(acs -> {
            List<Privilege> privileges = this.privilegeRepository.findByRole(acs.getAccessPK().getRole().getId());

            final var role = new RoleDTO();
            role.setName(acs.getAccessPK().getRole().getName());
            privileges.stream()
                        .filter(Privilege.class::isInstance)
                        .map(Privilege.class::cast)
                        .map(Privilege::getName)
                        .forEach(p -> role.getPrivileges().add("\""+p+"\""));
            final Map<String, Object> authorities = new HashMap<>();
            authorities.put("\""+APP_ID+"\"", "\""+acs.getAccessPK().getApplication().getAppID()+"\"");
            authorities.put("\""+ROLE+"\"", "\""+role.getName()+"\"");
            authorities.put("\""+PRIVILEGES+"\"", role.getPrivileges());
            roles.add(authorities);

        });
    }
}
