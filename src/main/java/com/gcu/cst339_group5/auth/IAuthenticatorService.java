package com.gcu.cst339_group5.auth;

public interface IAuthenticatorService {
	@Deprecated
    boolean authenticate(String username, String password);
    
    boolean authenticate(LoginRequest req);
}

