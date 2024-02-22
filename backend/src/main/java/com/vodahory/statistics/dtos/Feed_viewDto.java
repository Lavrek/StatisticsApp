package com.vodahory.statistics.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Feed_viewDto {
    @JsonProperty
    private int id;
    @JsonProperty
    private int count;
    @JsonProperty
    private String end;
    @JsonProperty
    private int error_status;
    @JsonProperty
    private String feed_name;
    @JsonProperty
    private int disappear;
    @JsonProperty
    private int is_sale;
    @JsonProperty
    private int new_product;
    @JsonProperty
    private int ok_status;
    @JsonProperty
    private int totalRevenue;
}
