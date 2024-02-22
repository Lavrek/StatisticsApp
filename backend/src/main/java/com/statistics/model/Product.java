package com.statistics.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class Product {
    @Id
    private int id;
    private String title;
    private String ean;
    private String name;
    private String part_number;
    private int product_id;

}
