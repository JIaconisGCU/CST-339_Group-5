package com.gcu.cst339_group5.user;

public interface IUserStore {

	boolean usernameExists(String username);

	boolean emailExists(String email);

	void save(User u);

	User findByUsername(String username);

}