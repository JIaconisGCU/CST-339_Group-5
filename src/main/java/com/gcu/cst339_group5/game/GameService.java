package com.gcu.cst339_group5.game;

import org.springframework.stereotype.Service;

/**
 * Service class for managing {@link Game} entities.
 * <p>
 * Provides methods to save and retrieve games using the
 * {@link GameRepository}. This layer abstracts repository details
 * from the controller.
 * </p>
 */
@Service
public class GameService {
    private final GameRepository repo;

    /**
     * Constructs a new {@code GameService} with the given repository.
     *
     * @param repo the GameRepository used for persistence
     */
    public GameService(GameRepository repo) {
        this.repo = repo;
    }

    /**
     * Saves a new game or updates an existing game.
     *
     * @param game the game to save
     * @return the persisted game
     */
    public Game save(Game game) { return repo.save(game); }

    /**
     * Retrieves all games in the catalog.
     *
     * @return iterable collection of games
     */
    public Iterable<Game> findAll() { return repo.findAll(); }
}
