package com.gcu.cst339_group5.controller;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import com.gcu.cst339_group5.user.User;
import com.gcu.cst339_group5.user.UserService;

/**
 * Handles the /login flow:
 *  - GET  /login : show login form
 *  - POST /login : authenticate user against DB
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    private final UserService userService;

    // Constructor injection
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    /** Trim incoming String fields; convert empty strings to null */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    // Show empty login form
    @GetMapping
    public String form(Model model) {
        model.addAttribute("pageTitle", "Login");
        if (!model.containsAttribute("user")) {
            model.addAttribute("user", new User());
        }
        return "login";
    }

    // Process login submission
    @PostMapping
    public String process(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("pageTitle", "Login");

        // Attempt login
        User existingUser = userService.login(user.getUsername(), user.getPassword());

        if (existingUser != null) {
            return "redirect:/";  // ✅ root path, handled by HomeController
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }
}
