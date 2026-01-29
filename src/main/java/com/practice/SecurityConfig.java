package com.practice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/actuator/health").permitAll() // public
                .requestMatchers("/actuator/**").authenticated() // secure others
                .anyRequest().permitAll() // rest of app public
            )
            .httpBasic(Customizer.withDefaults()) // basic auth for secured endpoints
            .csrf(csrf -> csrf.disable()); // disable CSRF for APIs

        return http.build();
    }
}
