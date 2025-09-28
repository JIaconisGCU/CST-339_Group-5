package com.gcu.cst339_group5.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * SecurityConfig sets up Spring Security rules for the app.
 * - Defines which pages are public vs. secure.
 * - Configures login and logout.
 * - Provides a password encoder (BCrypt).
 */
@Configuration
public class SecurityConfig {

    /**
     * PasswordEncoder bean for hashing user passwords.
     * This ensures passwords are stored in the DB securely (never plain text).
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Defines the security filter chain:
     * - Public access to login, register, error, CSS, and images.
     * - All other pages require authentication.
     * - Uses form-based login with custom login page (login.html).
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Authorization rules
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login", "/register", "/error", "/css/**", "/images/**").permitAll()
                .anyRequest().authenticated()
            )
            // Login configuration
            .formLogin(form -> form
                .loginPage("/login")                // custom Thymeleaf login page
                .defaultSuccessUrl("/games", true)  // where to go after successful login
                .permitAll()
            )
            // Logout configuration
            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout")  // redirect after logout
                .permitAll()
            );

        return http.build();
    }
}
