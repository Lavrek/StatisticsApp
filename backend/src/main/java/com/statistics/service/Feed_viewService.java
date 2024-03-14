package com.statistics.service;

import statistics.Feed;

import java.util.List;

/**
 * Service interface for managing feed views.
 */
public interface Feed_viewService {

    /**
     * Retrieves a list of all feed views.
     *
     * @return A list of {@link Feed} representing all feed views.
     */
    public List<Feed> getAllFeeds ();

    /**
     * Calculates the total revenue for a specific feed view.
     *
     * @param feed_id The ID of the feed view for which total revenue is requested.
     * @return The total revenue for the specified feed view.
     */
    public int totalRevenue(int feed_id);
}
