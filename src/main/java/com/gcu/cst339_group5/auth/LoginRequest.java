package com.gcu.cst339_group5.auth;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
	    @NotBlank(message = "Username is required") String username,
	    @NotBlank(message = "Password is required") String password
	) {
	public boolean isIncomplete() {
		return (username() == null || username().isBlank() || password() == null || password().isBlank());
	}
}

//@Deprecated
//public class LoginRequest {
//    @NotBlank(message = "Username is required")
//    private String username;
//
//    @NotBlank(message = "Password is required")
//    private String password;
//
//    public String getUsername() { return username; }
//    public void setUsername(String username) { this.username = username; }
//    public String getPassword() { return password; }
//    public void setPassword(String password) { this.password = password; }
//}
