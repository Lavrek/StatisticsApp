package com.statistics.repository;

import com.statistics.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repository interface for managing {@link Price} entities.
 */
@Repository
@Transactional
public interface PriceRepository extends JpaRepository<Price, Integer> {

    /**
     * Retrieves the solded product price value based on the given EAN (European Article Number).
     *
     * @param ean The EAN to search for.
     * @return The price value of the solded product matching the given EAN, or {@code null} if not found.
     */
    @Query("SELECT p.price_value FROM Price p WHERE p.ean = :ean ORDER BY p.id ASC LIMIT 1")
    @Transactional(readOnly = true)
    String findSoldedProductByEan(@Param("ean") String ean);
}
