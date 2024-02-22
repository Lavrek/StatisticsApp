package com.vodahory.statistics.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Entity
@Component
public class Disappear {
    @Id
    private int id;
    private String ean;
    private Long feed_view_id;
}
