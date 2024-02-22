package com.statistics.repository;

import com.statistics.model.Security;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The repository interface for managing {@link Security} entities.
 * This interface extends {@link org.springframework.data.jpa.repository.JpaRepository} to provide basic CRUD operations
 * and query functionality for the {@link Security} entity.
 *
 * <p>
 * This repository is responsible for interacting with the underlying database and performing operations
 * related to the {@link Security} entity.
 * </p>
 *
 * @author Ing. Ekaterina Lavrova
 * @version 1.0
 * @since 2023-09-05
 */

@EnableJpaRepositories
@Repository
public interface SecurityRepository extends JpaRepository<Security, Integer> {

    /**
     * Find a {@link Security} entity by its login and password.
     *
     * @param login    The login username to search for.
     * @param password The password associated with the login.
     * @return An {@link java.util.Optional} containing the found {@link Security} entity, or an empty Optional if not found.
     */
    Optional<Security> findOneByLoginAndPassword(String login, String password);

    /**
     * Find a {@link Security} entity by its login.
     *
     * @param login The login username to search for.
     * @return The found {@link Security} entity, or null if not found.
     */
    Security findByLogin(String login);
}
