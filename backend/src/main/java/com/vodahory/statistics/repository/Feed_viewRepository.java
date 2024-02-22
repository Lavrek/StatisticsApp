package com.vodahory.statistics.repository;
import com.vodahory.statistics.model.Feed_view;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing {@link Feed_view} entities.
 * <p>
 * This repository is responsible for interacting with the underlying database and performing operations
 * related to the Feed_view entity.
 * </p>
 *
 * @author Ing. Ekaterina Lavrova
 * @version 1.0
 * @since 2023-09-05
 */
@Repository
@Transactional
public interface Feed_viewRepository extends JpaRepository <Feed_view, Integer> {
}
