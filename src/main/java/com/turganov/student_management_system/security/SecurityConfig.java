package com.turganov.student_management_system.security;

import com.turganov.student_management_system.service.StudentDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final StudentDetailService studentDetailService;
    private final PasswordEncoder passwordEncoder;
    private final PasswordEncoderConfig passwordEncoderConfig;

    @Autowired
    public SecurityConfig(StudentDetailService studentDetailService, PasswordEncoder passwordEncoder, PasswordEncoderConfig passwordEncoderConfig) {
        this.studentDetailService = studentDetailService;
        this.passwordEncoder = passwordEncoder;
        this.passwordEncoderConfig = passwordEncoderConfig;
    }


    @Configuration
    public static class WebSecurityConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                    .requestMatchers("/student").authenticated()
                    .anyRequest().permitAll()
                    .and()
                    .formLogin();
        }
    }
}
