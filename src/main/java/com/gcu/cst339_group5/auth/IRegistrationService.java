package com.gcu.cst339_group5.auth;

import com.gcu.cst339_group5.user.User;

public interface IRegistrationService {
    /**
     * @return true when user saved; false when username/email taken
     */
    boolean register(User u);
    boolean usernameExists(String username);
    boolean emailExists(String email);
}
