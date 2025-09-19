package com.gcu.cst339_group5.game;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for {@link Game} persistence operations.
 * <p>
 * Extends Spring Data's {@link CrudRepository} to provide basic CRUD
 * functionality including save, find, update, and delete.
 * </p>
 */
@Repository
public interface GameRepository extends CrudRepository<Game, Long> {
    // Additional custom query methods can be added here if needed
}
