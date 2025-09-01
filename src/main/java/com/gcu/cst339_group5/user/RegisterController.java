package com.gcu.cst339_group5.user;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * Handles the /register flow:
 *  - GET  /register : show registration form
 *  - POST /register : validate and "save" (in memory for now), then redirect to login
 */
@Controller
@RequestMapping("/register")
public class RegisterController {

    private final InMemoryUserStore store;

    public RegisterController(InMemoryUserStore store) {
        this.store = store;
    }

    // Show empty form
    @GetMapping
    public String form(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    // Process submission
    @PostMapping
    public String process(@Valid @ModelAttribute("user") User user,
                          BindingResult result,
                          Model model) {

        // Run uniqueness checks only if field-level validation passed
        if (!result.hasErrors()) {
            if (store.usernameExists(user.getUsername())) {
                result.rejectValue("username", "duplicate", "Username already taken");
            }
            if (store.emailExists(user.getEmail())) {
                result.rejectValue("email", "duplicate", "Email already registered");
            }
        }

        // On any error, re-render form with messages
        if (result.hasErrors()) {
            return "register";
        }

        // Save to our dev store (plain password for now)
        store.save(user);

        // Redirect to login so Justin's code can set HttpSession on successful login
        return "redirect:/login?registered=1";
    }
}
