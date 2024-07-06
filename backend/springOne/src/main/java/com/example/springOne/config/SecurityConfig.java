package com.example.springOne.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/v1/star/log-in", "/api/v1/star/register", "/api/v1/star",
                        "/api/v1/star/profile", "/api/v1/star/log-out", "/api/v1/project/upload-project",
                        "/api/v1/star/image/upload", "/api/v1/star/image/get").permitAll()
                .anyRequest().authenticated() // Require authentication for all other endpoints
                .and()
                .httpBasic(); // Enable basic authentication
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

