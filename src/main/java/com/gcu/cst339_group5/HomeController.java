package com.gcu.cst339_group5;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  @GetMapping("/")
  public String home(Model model) {
    model.addAttribute("pageTitle", "Video Game Library");
    return "home"; // resolves to templates/home.html
  }
}
