package com.vodahory.statistics.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoodsDto {
    @JsonProperty
    private int product_id;
    @JsonProperty
    private String ean;
    @JsonProperty
    private String part_number;
    @JsonProperty
    private String title;
    @JsonProperty
    private String price_value;
    @JsonProperty
    private String currency;
    @JsonProperty
    private int availability_external;
    @JsonProperty
    private int availability_internal;
    @JsonProperty
    private int availability_manufacturer;


}

