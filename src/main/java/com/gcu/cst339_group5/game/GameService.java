package com.gcu.cst339_group5.game;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service layer for handling business logic related to {@link Game} entities.
 * <p>
 * Provides methods for retrieving, creating, updating, and deleting games.
 * Delegates persistence operations to the {@link GameRepository}.
 * </p>
 */
@Service
public class GameService {

    private final GameRepository gameRepository;

    /**
     * Constructs a new {@code GameService}.
     *
     * @param gameRepository the repository used for data persistence
     */
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    /**
     * Retrieves all games from the database.
     *
     * @return a list of all {@link Game} entities
     */
    public List<Game> findAll() {
        return (List<Game>) gameRepository.findAll();
    }

    /**
     * Saves a new game to the database.
     *
     * @param game the {@link Game} to save
     * @return the saved {@link Game}
     */
    public Game save(Game game) {
        return gameRepository.save(game);
    }

    /**
     * Retrieves a single game by its ID.
     *
     * @param id the ID of the {@link Game} to retrieve
     * @return the {@link Game} if found, or {@code null} if not found
     */
    public Game getGameById(Long id) {
        Optional<Game> result = gameRepository.findById(id);
        return result.orElse(null);
    }

    /**
     * Updates an existing game in the database.
     * <p>
     * Since {@link GameRepository#save(Object)} handles both create and update,
     * this method delegates to {@code save()}.
     * </p>
     *
     * @param game the {@link Game} containing updated values
     * @return the updated {@link Game}
     */
    public Game updateGame(Game game) {
        return gameRepository.save(game);
    }

    /**
     * Deletes an existing game by its ID.
     *
     * @param id the ID of the {@link Game} to delete
     */
    public void deleteGame(Long id) {
        gameRepository.deleteById(id);
    }
}
