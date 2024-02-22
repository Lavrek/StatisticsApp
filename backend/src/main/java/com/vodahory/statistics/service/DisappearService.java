package com.vodahory.statistics.service;

import com.vodahory.statistics.dtos.GoodsDto;

import java.util.List;

/**
 * Service interface for managing disappeared goods.
 */
public interface DisappearService {

    /**
     * Retrieves a list of selected goods that have disappeared based on the given feed view ID.
     *
     * @param feed_id The feed view ID to filter disappeared goods.
     * @return A list of {@link GoodsDto} representing selected disappeared goods associated with the given feed view ID.
     */
    public List<GoodsDto> getSelectedSoldGoods(Long feed_id);
}
