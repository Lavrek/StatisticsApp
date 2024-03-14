package com.statistics.mapper;

import com.statistics.model.Goods;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import statistics.Item;

/**
 * Mapper interface to convert between Item DTO (Data Transfer Object) and Goods entity.
 * Utilizes MapStruct for mapping between source and destination objects.
 */
@Mapper(componentModel = "spring")

public interface GoodsDtoToGoods {
    /**
     * Converts a Goods entity to an Item DTO.
     *
     * @param source The Goods entity to be converted.
     * @return The resulting Item DTO.
     */

    @Mapping(target = "partNumber", source = "source.part_number")
    @Mapping(target = "productId", source = "source.product_id")
    @Mapping(target = "priceValue", source = "source.price_value")
    @Mapping(target = "availabilityExternal", source = "source.availability_external")
    @Mapping(target = "availabilityInternal", source = "source.availability_internal")
    @Mapping(target = "availabilityManufacturer", source = "source.availability_manufacturer")
    Item goodsToGoodsDto(Goods source);

    /**
     * Converts an Item DTO to a Goods entity.
     *
     * @param destination The Item DTO to be converted.
     * @return The resulting Goods entity.
     */
    @InheritInverseConfiguration
    Goods goodsDtoToGoods(Item destination);

}


