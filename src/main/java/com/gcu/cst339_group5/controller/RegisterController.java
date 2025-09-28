package com.gcu.cst339_group5.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.crypto.password.PasswordEncoder;   //  NEW import
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import com.gcu.cst339_group5.user.User;
import com.gcu.cst339_group5.user.UserService;

/**
 * Handles the /register flow:
 *  - GET  /register : show registration form
 *  - POST /register : validate and save to DB, then redirect to login
 */
@Controller
@RequestMapping("/register")
public class RegisterController {

    private final UserService userService;

    // Inject PasswordEncoder from SecurityConfig
    @Autowired
    private PasswordEncoder passwordEncoder;   // ⬅️ NEW field

    // Constructor injection (Spring will auto-wire UserService)
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    /** Trim incoming String fields; convert empty strings to null */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    // Show empty form
    @GetMapping
    public String form(Model model) {
        model.addAttribute("pageTitle", "Register");
        if (!model.containsAttribute("user")) {
            model.addAttribute("user", new User());
        }
        return "register";
    }

    // Process submission
    @PostMapping
    public String process(@Valid @ModelAttribute("user") User user,
                          BindingResult result,
                          Model model) {

        model.addAttribute("pageTitle", "Register");

        // Basic validation errors handled by annotations
        if (result.hasErrors()) {
            return "register";
        }

        // ✅ Encode the password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Persist the new user in the database
        userService.register(user);

        // Redirect to login so user can log in with new credentials
        return "redirect:/login?registered=1";
    }
}
