package com.gmail.andersoninfonet.authserver.config;

import java.util.Arrays;

import com.gmail.andersoninfonet.authserver.security.JwtRequestFilter;
import com.gmail.andersoninfonet.authserver.service.AuthUserDetailService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    private final AuthUserDetailService authUserDetailService;

    private final JwtRequestFilter jwtRequestFilter;

    private final Environment environment;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        final var provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(this.authUserDetailService);
		provider.setPasswordEncoder(passwordEncoder());
		auth.authenticationProvider(provider);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
        .ignoring()
        .antMatchers(HttpMethod.POST, "/access/v1/login");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //config to allow h2-console render
        if(Arrays.asList(this.environment.getActiveProfiles()).contains("test")) {
            http.headers().frameOptions().disable();
            http.authorizeRequests().antMatchers("/h2-console/**").permitAll();
        }

        http.csrf().disable()
            .authorizeRequests()
            .antMatchers("/access/v1/login").permitAll()
            .anyRequest().authenticated()
        .and()
            .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
