package com.gcu.cst339_group5.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	// Custom query method
	User findByUsername(String username);
}