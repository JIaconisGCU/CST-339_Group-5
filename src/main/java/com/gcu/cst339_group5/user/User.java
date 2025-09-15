package com.gcu.cst339_group5.user;

import jakarta.validation.constraints.*;
import org.springframework.data.annotation.Id;  
import org.springframework.data.relational.core.mapping.Table;

/**
 * User entity class for Registration + Login.
 * Combines form validation with Spring Data JDBC persistence mapping.
 */
@Table("users") // maps this class to the "users" table
public class User {

    // --- BASIC PROFILE ---
	
	// --- PRIMARY KEY ---
    @Id
    private Long id; // Auto-generated in DB

    @NotBlank(message = "First name is required")
    @Size(max = 50, message = "First name must be at most 50 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 50, message = "Last name must be at most 50 characters")
    private String lastName;

    // --- CONTACT INFO ---

    @NotBlank(message = "Email is required")
    @Email(message = "Enter a valid email address")
    @Size(max = 254, message = "Email is too long")
    private String email;

    /**
     * US-style formats allowed:
     * (123) 456-7890  |  123-456-7890  |  1234567890
     * Keep it simple until we localize.
     */
    @NotBlank(message = "Phone is required")
    @Pattern(
        regexp = "^(\\(\\d{3}\\)\\s?\\d{3}-\\d{4}|\\d{3}-\\d{3}-\\d{4}|\\d{10})$",
        message = "Use (123) 456-7890, 123-456-7890, or 1234567890"
    )
    @Size(max = 20, message = "Phone is too long")
    private String phone;

    // --- CREDENTIALS ---

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 30, message = "Username must be 3–30 characters")
    private String username;

    /**
     * Plain text here ONLY for the prototype. In real auth we’ll hash with BCrypt.
     * 8+ chars keeps it simple for the assignment.
     */
    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 72, message = "Password must be at least 8 characters")
    private String password;
    
 // --- Constructors ---
    public User() {}

    public User(Long id, String firstName, String lastName,
                String email, String phone,
                String username, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.username = username;
        this.password = password;
    }


    // --- Getters / Setters ---
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
