package com.vodahory.statistics.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Availability {
    @Id
    private int id;
    private String ean;
    private int external;
    private int internal;
    private int manufacturer;
}
