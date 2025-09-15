package com.gcu.cst339_group5.game;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for {@link Game} entities.
 * <p>
 * Extends {@link CrudRepository} to provide basic CRUD operations.
 * Spring Data JDBC automatically generates the implementation.
 * </p>
 */
@Repository
public interface GameRepository extends CrudRepository<Game, Long> {
}
