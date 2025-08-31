package com.gcu.cst339_group5.user;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Temporary "repository" for development!!!!
 * Swap with a real DB repo later (JDBC/JPA).
 */
@Component
public class InMemoryUserStore {

    // Lookup by lowercase username/email to make checks case-insensitive
    private final Map<String, User> byUsername = new ConcurrentHashMap<>();
    private final Map<String, User> byEmail = new ConcurrentHashMap<>();

    public boolean usernameExists(String username) {
        return username != null && byUsername.containsKey(username.toLowerCase());
    }

    public boolean emailExists(String email) {
        return email != null && byEmail.containsKey(email.toLowerCase());
    }

    public void save(User u) {
        byUsername.put(u.getUsername().toLowerCase(), u);
        byEmail.put(u.getEmail().toLowerCase(), u);
    }

    public User findByUsername(String username) {
        return username == null ? null : byUsername.get(username.toLowerCase());
    }
}
