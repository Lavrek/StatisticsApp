package com.statistics.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Feed_view {
    @Id
    private int id;
    private int count;
    private int disappear;
    private String end;
    private int error_status;
    private String feed_name;
    private int is_new;
    private int is_outlet;
    private int is_sale;
    private int new_product;
    private int ok_status;
    private Integer totalRevenue;
}
