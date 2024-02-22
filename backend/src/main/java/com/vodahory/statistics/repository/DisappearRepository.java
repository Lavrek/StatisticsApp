package com.vodahory.statistics.repository;

import com.vodahory.statistics.model.Disappear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Repository interface for managing {@link Disappear} entities.
 */
@Repository
public interface DisappearRepository extends JpaRepository<Disappear, Integer> {

    /**
     * Retrieves a list of EANs (European Article Numbers) for products that have disappeared
     * based on the given feed view ID.
     *
     * @param feed_id The feed view ID to search for disappeared products.
     * @return A list of EANs for disappeared products associated with the given feed view ID.
     */
    @Query("SELECT d.ean FROM Disappear d WHERE d.feed_view_id = :feed_id")
    @Transactional(readOnly = true)
    List<String> findSoldedProductsEans(@Param("feed_id") Long feed_id);

}
