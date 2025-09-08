package com.gcu.cst339_group5;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // Map HTTP GET requests for the root path "/" to this method.
	// This means when a user visits http://localhost:8080/, this method will execute.
  @GetMapping("/")
  public String home(Model model) {
	  
	 // "pageTitle" is the key, "Video Game Library" is the value.
	 // This allows the Thymeleaf template (home.html) to access ${pageTitle}.
    model.addAttribute("pageTitle", "Video Game Library");
    return "home"; // resolves to templates/home.html
  }
}
