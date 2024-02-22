package com.vodahory.statistics.repository;

import com.vodahory.statistics.model.Description;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Repository interface for managing {@link Description} entities.
 */
@Repository
public interface DescriptionRepository extends JpaRepository<Description, Integer> {

    /**
     * Retrieves a list of descriptions based on the given EAN (European Article Number).
     *
     * @param ean The EAN to search for.
     * @return A list of {@link Description} entities matching the given EAN.
     */
    @Query("SELECT d FROM Description d WHERE d.ean = :ean")
    @Transactional(readOnly = true)
    List<Description> descriptiontByEan(@Param("ean") String ean);
}
