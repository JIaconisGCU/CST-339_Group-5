package com.gcu.cst339_group5;

import com.gcu.cst339_group5.auth.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Handles login without a form-backing object.
 * Reads username/password directly from request params posted by login.html.
 * Week 3: simple session flag instead of Spring Security.
 */
@Controller
public class LoginController {

    private final AuthService auth;

    // Constructor injection (Spring Core / DI)
    public LoginController(AuthService auth) {
        this.auth = auth;
    }

    /** Render login page */
    @GetMapping("/login")
    public String form(Model m) {
        m.addAttribute("pageTitle", "Login");
        return "login";
    }

    /** Handle login submission */
    @PostMapping("/login")
    public String submit(@RequestParam String username,
                         @RequestParam String password,
                         HttpSession session,
                         Model m,
                         RedirectAttributes ra) {

        if (username == null || username.isBlank() || password == null || password.isBlank()) {
            m.addAttribute("pageTitle", "Login");
            m.addAttribute("error", "Username and password are required");
            return "login";
        }

        if (!auth.authenticate(username, password)) {
            m.addAttribute("pageTitle", "Login");
            m.addAttribute("error", "Invalid username or password");
            return "login";
        }

        session.setAttribute("username", username);
        ra.addFlashAttribute("message", "Welcome, " + username + "!");
        return "redirect:/";
    }

    /** Optional: simple logout endpoint */
    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes ra) {
        session.invalidate();
        ra.addFlashAttribute("message", "You have been logged out.");
        return "redirect:/login";
    }
}

