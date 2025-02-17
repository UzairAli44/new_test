package com.example.fidely.fidely.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    @Value("${couchdb.role}")
    private String couchDbRole;

    @Value("${couchdb.username}")
    private String couchDbUsername;

    @Value("${couchdb.password}")
    private String couchDbPassword;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) // Disable CSRF protection
            .authorizeHttpRequests(requests -> requests
                .requestMatchers("/auth/**", "/register", "/signup").permitAll() // Allow these endpoints
                .anyRequest().authenticated() // Secure all other endpoints
            )
            .formLogin(form -> form.disable()) // Disable default form login
            .httpBasic(basic -> basic.disable()); // Disable basic authentication

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Use BCrypt for password encoding
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> User.builder()
            .username(couchDbUsername)
            .password(passwordEncoder().encode(couchDbPassword))
            .roles(couchDbRole)
            .build();
    }
}
