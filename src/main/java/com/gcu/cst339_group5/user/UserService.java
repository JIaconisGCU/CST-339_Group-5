package com.gcu.cst339_group5.user;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
*Service layer managing User registration and login
* Uses UserRepository (Spring Data JDBC) for persistence.
*/
@Service
public class UserService {
	private final UserRepository userRepository;
	
	private final PasswordEncoder encoder;
	
	// Constructor injection (Spring will auto-wire this)
	public UserService(UserRepository userRepository, PasswordEncoder encoder) {
		this.userRepository = userRepository;
		this.encoder = encoder;
	}
	
	/**
	 * Register a new user
	 * @param user User object from registration form
	 * @return saved User object with generated ID
	 */
	public User register(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		if (user.getRole() == null) user.setRole("ROLE_USER");
		if (user.getEnabled() == null) user.setEnabled(true);
		return userRepository.save(user);
	}
	
	/**
	 * Attempt to log in by username + password
	 * @param username login username
	 * @param password login password (plain text for now)
	 * @return matching User if credential are valid, else null
	 */
	public User login(String username, String password) {
		User found = userRepository.findByUsername(username);
		if(found != null && found.getPassword().equals(password)) {
			return found;
		}
		return null;
	}
	
	public boolean credentialsValid(String username, String rawPassword) {
		User found = userRepository.findByUsername(username);
		return (found != null) && encoder.matches(rawPassword, found.getPassword());
	}
}