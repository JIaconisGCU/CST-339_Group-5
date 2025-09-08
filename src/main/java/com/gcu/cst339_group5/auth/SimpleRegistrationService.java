package com.gcu.cst339_group5.auth;

import com.gcu.cst339_group5.user.InMemoryUserStore;
import com.gcu.cst339_group5.user.User;
import org.springframework.stereotype.Service;

@Service
public class SimpleRegistrationService implements IRegistrationService {
    private final InMemoryUserStore store;

    public SimpleRegistrationService(InMemoryUserStore store) {
        this.store = store;
    }

    public boolean register(User u) {
        if (u == null) return false;
        if (usernameExists(u.getUsername()) || emailExists(u.getEmail())) return false;
        store.save(u);
        return true;
    }

    public boolean usernameExists(String username) { return store.usernameExists(username); }
    public boolean emailExists(String email) { return store.emailExists(email); }
}
