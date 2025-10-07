package com.gcu.cst339_group5.game;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/games")
public class GameApiController {

    private final GameService gameService;

    public GameApiController(GameService gameService) {
        this.gameService = gameService;
    }

    /** REST API 1: Return all products (games) */
    @GetMapping
    public List<GameDTO> getAll() {
        return gameService.findAll().stream().map(GameDTO::from).toList();
    }

    /** REST API 2: Return a desired product by id */
    @GetMapping("/{id}")
    public GameDTO getById(@PathVariable Long id) {
        Game g = gameService.getGameById(id); // your service returns null if not found
        if (g == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found");
        }
        return GameDTO.from(g);
    }
}
