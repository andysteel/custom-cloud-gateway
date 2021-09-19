package com.gmail.andersoninfonet.authserver.repository;

import com.gmail.andersoninfonet.authserver.model.AuthUser;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class AuthUserRepositoryTest {

    @Autowired
    private AuthUserRepository authUserRepository;

    @Test
    void testFindByLogin() {
        final AuthUser user = this.authUserRepository.findByLogin("andersoninfonet@gmail.com").get();

        Assertions.assertThat(user).isNotNull();
        Assertions.assertThat(user.getLogin()).isNotEmpty();
        Assertions.assertThat(user.getLogin()).isNotBlank();
        Assertions.assertThat(user.getLogin()).isEqualTo("andersoninfonet@gmail.com");
    }
}
