package com.statistics.service;

import com.statistics.dtos.GoodsDto;
import com.statistics.model.*;
import com.statistics.repository.AvailabilityRepository;
import com.statistics.repository.DescriptionRepository;
import com.statistics.repository.PriceRepository;
import com.statistics.repository.ProductRepository;
import com.statistics.exceptions.GoodsNotFoundException;
import com.statistics.mapper.GoodsDtoToGoods;
import com.statistics.model.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GoodsServiceImpl implements GoodsService {
    private final ProductRepository productRepository;
    private final PriceRepository priceRepository;
    private final AvailabilityRepository availabilityRepository;
    private final DescriptionRepository descriptionRepository;
    GoodsDtoToGoods goodsDtoToGoods;
    Price price;

    @Override
    public List<GoodsDto> getAllGoods() {
        List<Product> listOfProducts = productRepository.findAll().stream().toList();
        List<Price> listOfPrices = priceRepository.findAll().stream().toList();
        List<Availability> listOfAvailability = availabilityRepository.findAll().stream().toList();

        Map<String, String> priceMap = listOfPrices.stream()
                .collect(Collectors.toMap(Price::getEan, Price::priceWithCurrency, (existing, replacement) -> replacement));

        Map<String, Availability> availabilityMap = listOfAvailability.stream()
                .collect(Collectors.toMap(Availability::getEan, Function.identity()));

        List<GoodsDto> listOfGoodsFromProducts = listOfProducts.stream()
                .map(product -> {
                    Goods goods = new Goods();
                    goods.setId(product.getId());
                    goods.setEan(product.getEan());
                    goods.setTitle(product.getTitle());
                    goods.setName(product.getName());
                    goods.setPart_number(product.getPart_number());
                    goods.setProduct_id(product.getProduct_id());

                    String priceValue = priceMap.get(product.getEan());
                    if (priceValue != null) {
                        goods.setPrice_value(priceValue);
                    }
                    Availability availability = availabilityMap.get(product.getEan());
                    goods.setAvailability_external(availability.getExternal());
                    goods.setAvailability_internal(availability.getInternal());
                    goods.setAvailability_manufacturer(availability.getManufacturer());
                    return goods;
                })
                .map(goodsDtoToGoods::goodsToGoodsDto)
                .collect(Collectors.toList());
        if (listOfGoodsFromProducts.isEmpty()) throw new GoodsNotFoundException();
        return listOfGoodsFromProducts;
    }

    @Override
    public List<Description> getDescription(String ean) {
        return descriptionRepository.descriptiontByEan(ean);
    }
}

