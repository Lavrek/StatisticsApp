package com.statistics.repository;

import com.statistics.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing {@link Product} entities.
 */
@Repository
public interface ProductRepository extends JpaRepository <Product, Integer> {

}
