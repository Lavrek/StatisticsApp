package com.vodahory.statistics.repository;

import com.vodahory.statistics.model.Availability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repository interface for managing {@link Availability} entities.
 */
@Repository
public interface AvailabilityRepository extends JpaRepository<Availability, Integer> {

    /**
     * Finds the availability for a given EAN (European Article Number).
     *
     * @param ean The EAN to search for.
     * @return The availability matching the given EAN, or {@code null} if not found.
     */
    @Query("SELECT a FROM Availability a WHERE a.ean = :ean ORDER BY a.id ASC LIMIT 1")
    @Transactional(readOnly = true)
    Availability findAvailabilityByEans(@Param("ean") String ean);
}
