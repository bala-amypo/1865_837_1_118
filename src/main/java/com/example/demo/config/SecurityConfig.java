package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            // disable csrf
            .csrf(csrf -> csrf.disable())

            // allow everything without authentication
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()
            )

            // 🚫 disable default login popup
            .httpBasic(httpBasic -> httpBasic.disable())

            // 🚫 disable form login
            .formLogin(form -> form.disable());

        return http.build();
    }
}
