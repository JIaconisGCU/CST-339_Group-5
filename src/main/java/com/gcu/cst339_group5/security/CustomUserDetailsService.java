package com.gcu.cst339_group5.security;

import com.gcu.cst339_group5.user.User;
import com.gcu.cst339_group5.user.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * CustomUserDetailsService integrates our User entity
 * with Spring Security authentication.
 * It fetches user data from the database by username
 * and adapts it into a Spring Security UserDetails object.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    // Constructor injection for UserRepository
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Loads user data by username.
     * Throws an exception if the user is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        // Wrap our User entity into Spring Security's UserDetails object.
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword()) // BCrypt from DB
                .authorities(user.getRole()) // e.g., ROLE_USER or ROLE_ADMIN
                .disabled(Boolean.FALSE.equals(user.getEnabled()) ? false : !user.getEnabled())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .build();
    }
}
