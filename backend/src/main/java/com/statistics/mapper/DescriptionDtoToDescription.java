package com.statistics.mapper;

import org.mapstruct.Mapper;
import statistics.Description;

/**
 * Mapper interface to convert between Description DTO (Data Transfer Object) and Description entity.
 * Utilizes MapStruct for mapping between source and destination objects.
 */

@Mapper(componentModel = "spring")
public interface DescriptionDtoToDescription {
    /**
     * Converts a Description entity to a Description DTO.
     *
     * @param entity The Description entity to be converted.
     * @return The resulting Description DTO.
     */
    Description descriptionToDescriptionDto(com.statistics.model.Description entity);

    /**
     * Converts a Description DTO to a Description entity.
     *
     * @param destination The Description DTO to be converted.
     * @return The resulting Description entity.
     */
    com.statistics.model.Description DescriptionDtoToDescription(Description destination);
}
