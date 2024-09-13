package com.example.user.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Disable CSRF protection for API-based login (you may want this enabled for stateful sessions)
        http.csrf(csrf -> csrf.disable())
                // Authorization configuration
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/users/register", "/users/login").permitAll() // Allow public access to these endpoints
                        .anyRequest().authenticated()  // Require authentication for other requests
                )
                // Disable default form login (since you may be using JWT or a custom login system)
                .formLogin(form -> form.disable());

        return http.build();
    }
}
