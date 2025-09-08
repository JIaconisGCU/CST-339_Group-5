package com.gcu.cst339_group5.auth;

import com.gcu.cst339_group5.user.InMemoryUserStore;
import com.gcu.cst339_group5.user.User;
import org.springframework.stereotype.Service;

// TODO encryption (after week 3)
@Service
public class HardcodedAuthService implements IAuthenticatorService {
    private final InMemoryUserStore store;
    
    public HardcodedAuthService(InMemoryUserStore store) {
        this.store = store; 
        
        //TEMP demo account #1
        if (!store.usernameExists("admin")) {
            User u = new User();
            u.setFirstName("admin");
            u.setLastName("User");
            u.setEmail("admin@example.com");
            u.setPhone("555-0000");
            u.setUsername("admin");
            u.setPassword("password");
            store.save(u);
        }
        
        //TEMP demo account #2
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

    @Override @Deprecated
    public boolean authenticate(String username, String password) {
        User u = store.findByUsername(username);
        return u != null && password.equals(u.getPassword());
    }
    
    @Override
    public boolean authenticate(LoginRequest req) {
    	User user = store.findByUsername(req.username());
    	return (user != null) && (req.password().equals(user.getPassword()));
    }
}
