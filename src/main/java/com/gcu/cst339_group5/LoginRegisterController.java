package com.gcu.cst339_group5;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginRegisterController {
  @GetMapping("/login")
  public String login() { return "login"; }

  @GetMapping("/register")
  public String register() { return "register"; }
}
