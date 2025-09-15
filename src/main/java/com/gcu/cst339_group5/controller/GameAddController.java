package com.gcu.cst339_group5.controller;

import com.gcu.cst339_group5.game.Game;
import com.gcu.cst339_group5.game.GameService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

/**
 * Controller for handling requests related to adding new games.
 * <p>
 * Provides endpoints for displaying the "add game" form and processing
 * form submissions. Uses {@link GameService} to persist new games in
 * the database.
 * </p>
 */
@Controller
@RequestMapping("/games")
public class GameAddController {
	
    private final GameService gameService;

    /**
     * Constructs a new {@code GameAddController}.
     *
     * @param gameService the service used to handle game persistence
     */
    public GameAddController(GameService gameService) {
        this.gameService = gameService; 
    }

    /**
     * Displays the form for creating a new game.
     *
     * @param model the model to which a new {@link Game} object is added
     * @return the Thymeleaf view for the add game form
     */
    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("game", new Game());
        return "games/game-add-form";
    }

    /**
     * Handles submission of the add game form.
     *
     * @param game the {@link Game} submitted by the user
     * @param bindingResult contains validation results for the submitted form
     * @param model the model used to pass data back to the view
     * @return redirect to confirmation or form with errors
     */
    @PostMapping
    public String submitForm(@Valid @ModelAttribute("game") Game game,
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            return "games/game-add-form";
        }

        // Save the game using the service layer
        gameService.save(game);

        // Redirect to new entry page with success flag
        return "redirect:/games/new?success";
    }
}
