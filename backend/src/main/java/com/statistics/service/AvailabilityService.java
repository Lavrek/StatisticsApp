package com.statistics.service;

import com.statistics.model.Availability;

/**
 * Service interface for managing the availability of products.
 */
public interface AvailabilityService {

    /**
     * Retrieves the availability of a product based on its EAN (European Article Number).
     *
     * @param ean The EAN of the product for which availability is requested.
     * @return The availability information for the product with the given EAN,
     *         or {@code null} if the product is not found.
     */
    public Availability getAvailability(String ean);
}
