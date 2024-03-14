package com.statistics.service;

import com.statistics.model.Description;
import statistics.Item;

import java.util.List;

/**
 * Service interface for managing goods.
 */
public interface GoodsService {

    /**
     * Retrieves a list of all goods.
     *
     * @return A list of {@link Item} representing all goods.
     */
    public List<Item> getAllGoods();

    /**
     * Retrieves the descriptions for a specific product based on its EAN (European Article Number).
     *
     * @param ean The EAN of the product for which descriptions are requested.
     * @return A list of {@link Description} entities representing descriptions for the specified product.
     */
    public List<Description> getDescription(String ean);
}
