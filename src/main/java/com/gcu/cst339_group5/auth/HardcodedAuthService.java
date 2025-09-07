package com.gcu.cst339_group5.auth;

import com.gcu.cst339_group5.user.InMemoryUserStore;
import com.gcu.cst339_group5.user.User;
import org.springframework.stereotype.Service;

@Service
public class HardcodedAuthService implements AuthService {
    private final InMemoryUserStore store;

    public HardcodedAuthService(InMemoryUserStore store) {
        this.store = store; 
        // Seed a couple accounts for demo
        if (!store.usernameExists("admin")) {
            User u = new User();
            u.setFirstName("admin");
            u.setLastName("User");
            u.setEmail("admin@example.com");
            u.setPhone("555-0000");
            u.setUsername("admin");
            u.setPassword("password"); // ok to store plain for Week 3
            store.save(u);
        }
        if (!store.usernameExists("test")) {
            User t = new User();
            t.setFirstName("Test");
            t.setLastName("User");
            t.setEmail("test@example.com");
            t.setPhone("555-1111");
            t.setUsername("test");
            t.setPassword("Test123!");
            store.save(t);
        }
    }

    @Override
    public boolean authenticate(String username, String password) {
        User u = store.findByUsername(username);
        return u != null && password.equals(u.getPassword());
    }
}
