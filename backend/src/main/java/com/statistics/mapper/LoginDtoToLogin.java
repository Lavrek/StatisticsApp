package com.statistics.mapper;

import com.statistics.model.Security;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import statistics.LoginDto;

/**
 * Mapper interface to convert between Item DTO (Data Transfer Object) and Goods entity.
 * Utilizes MapStruct for mapping between source and destination objects.
 */
@Mapper(componentModel = "spring")

public interface LoginDtoToLogin {

    /**
     * Converts a Login entity to a Login DTO.
     *
     * @param source The Login entity to be converted.
     * @return The resulting Login DTO.
     */
    LoginDto loginToLoginDto(Security source);

    /**
     * Converts a Login DTO to a Login entity.
     *
     * @param destination The Login DTO to be converted.
     * @return The resulting Login entity.
     */
    Security loginDtoToLogin(LoginDto destination);

}
