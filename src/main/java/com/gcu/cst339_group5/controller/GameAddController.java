package com.gcu.cst339_group5.controller;
import com.gcu.cst339_group5.game.Game;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.validation.Valid;

@Controller
public class GameAddController {

    @GetMapping("/games/new")
    public String showForm(Model model) {
        model.addAttribute("game", new Game());
        return "game-add-form";
    }

    @PostMapping("/games")
    public String submitForm(@Valid Game game, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "game-add-form";
        }

        // TODO here is where to handle the new `game` object eg add to database
        System.out.println("Game added: " + game.getTitle());

        //return "redirect:/games";
        return "redirect:/games/new?success"; //TEMP will redirect to new entry page in archive
    }
}
