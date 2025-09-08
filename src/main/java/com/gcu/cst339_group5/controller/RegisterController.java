package com.gcu.cst339_group5.user;

import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
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

        // Run uniqueness checks only if those fields passed basic validation
        if (!result.hasFieldErrors("username") && store.usernameExists(user.getUsername())) {
            result.rejectValue("username", "taken", "Username is already taken");
        }
        if (!result.hasFieldErrors("email") && store.emailExists(user.getEmail())) {
            result.rejectValue("email", "taken", "Email is already registered");
        }

        // On any error, re-render form with messages
        if (result.hasErrors()) {
            return "register";
        }

        // Save to our dev store (plain password for Week 3)
        store.save(user);

        // Redirect to login so session can be set after successful login
        return "redirect:/login?registered=1";
    }
}
