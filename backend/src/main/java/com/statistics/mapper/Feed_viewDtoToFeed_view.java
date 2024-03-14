package com.statistics.mapper;

import com.statistics.model.Feed_view;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import statistics.Feed;

/**
 * Mapper interface to convert between Feed DTO (Data Transfer Object) and Feed_view entity.
 * Utilizes MapStruct for mapping between source and destination objects.
 */
@Mapper(componentModel = "spring")
public interface Feed_viewDtoToFeed_view {
    /**
     * Converts a Feed_view to a Feed entity.
     *
     * @param entity The Feed_view entity to be converted.
     * @return The resulting Feed_view DTO.
     */
    @Mapping(target = "errorStatus", source = "entity.error_status")
    @Mapping(target = "feedName", source = "entity.feed_name")
    @Mapping(target = "isNew", source = "entity.is_new")
    @Mapping(target = "isOutlet", source = "entity.is_outlet")
    @Mapping(target = "isSale", source = "entity.is_sale")
    @Mapping(target = "newProduct", source = "entity.new_product")
    @Mapping(target = "okStatus", source = "entity.ok_status")
    Feed feed_viewToFeed_viewDto(Feed_view entity);

    /**
     * Converts a Feed_view DTO to a Feed_view entity.
     *
     * @param destination The Feed_view DTO to be converted.
     * @return The resulting Feed_view entity.
     */

    @InheritInverseConfiguration
    Feed_view feed_viewDtoToFeed_view(Feed destination);
}
