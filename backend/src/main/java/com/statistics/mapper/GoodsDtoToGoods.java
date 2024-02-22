package com.statistics.mapper;

import com.statistics.dtos.GoodsDto;
import com.statistics.model.Goods;
import org.mapstruct.Mapper;

/**
 * Mapper interface to convert between Goods DTO (Data Transfer Object) and Goods entity.
 * Utilizes MapStruct for mapping between source and destination objects.
 */
@Mapper(componentModel = "spring")

public interface GoodsDtoToGoods {
    /**
     * Converts a Goods entity to a Goods DTO.
     *
     * @param source The Goods entity to be converted.
     * @return The resulting Goods DTO.
     */
    GoodsDto goodsToGoodsDto(Goods source);

    /**
     * Converts a Goods DTO to a Goods entity.
     *
     * @param destination The Goods DTO to be converted.
     * @return The resulting Goods entity.
     */
    Goods goodsDtoToGoods(GoodsDto destination);

}


