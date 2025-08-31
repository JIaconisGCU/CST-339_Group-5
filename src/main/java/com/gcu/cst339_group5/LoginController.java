package com.gcu.cst339_group5;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	
	private String testUsername = "admin";
	private String testPassword = "password";

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login") //TEMP fake authentication
    public String processLogin(@RequestParam String username,
                               @RequestParam String password,
                               HttpSession session,
                               Model model) {
        if (username.equals(testUsername) && password.equals(testPassword)) {
            session.setAttribute("username", username);
            return "redirect:/games";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
