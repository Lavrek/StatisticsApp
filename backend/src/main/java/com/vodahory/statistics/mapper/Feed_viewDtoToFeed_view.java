package com.vodahory.statistics.mapper;

import com.vodahory.statistics.dtos.Feed_viewDto;
import com.vodahory.statistics.model.Feed_view;
import org.mapstruct.Mapper;

/**
 * Mapper interface to convert between Goods DTO (Data Transfer Object) and Goods entity.
 * Utilizes MapStruct for mapping between source and destination objects.
 */
@Mapper(componentModel = "spring")
public interface Feed_viewDtoToFeed_view {
    /**
     * Converts a Feed_view entity to a Feed_view DTO.
     *
     * @param source The Feed_view entity to be converted.
     * @return The resulting Feed_view DTO.
     */
    Feed_viewDto feed_viewToFeed_viewDto(Feed_view source);

    /**
     * Converts a Feed_view DTO to a Feed_view entity.
     *
     * @param destination The Feed_view DTO to be converted.
     * @return The resulting Feed_view entity.
     */
    Feed_view feed_viewDtoToFeed_view(Feed_viewDto destination);
}
