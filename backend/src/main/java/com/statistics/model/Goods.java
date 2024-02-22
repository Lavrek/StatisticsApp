package com.statistics.model;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;


@Setter
@Getter
@Component
public class Goods {
    @Id
    private int id;
    private String name;
    private String part_number;
    private int product_id;
    private String ean;
    private String title;
    private String price_value;
    private int availability_external;
    private int availability_internal;
    private int availability_manufacturer;


    public Goods goodsByProduct(Product product) {
        Goods fromProduct = new Goods();
        fromProduct.id = product.getId();
        fromProduct.title = product.getTitle();
        fromProduct.ean = product.getEan();
        return fromProduct;
    }

    public Goods goodsByPrice(Price price, Goods fromProduct) {
        if (fromProduct.getEan().equals(price.getEan())) {
            fromProduct.setPrice_value(price.getPrice_value().substring(0, 4));
        }
        return fromProduct;
    }

    public int getId(Goods goods) {
        return goods.id;
    }
}

