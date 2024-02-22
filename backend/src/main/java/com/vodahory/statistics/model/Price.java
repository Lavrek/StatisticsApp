package com.vodahory.statistics.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Entity
@Component
public class Price {
    @Id
    private int id;
    private String price_value;
    private String ean;
    private String currency;
    public String priceWithCurrency(){
        return this.price_value + " " + this.currency;
    }
}
