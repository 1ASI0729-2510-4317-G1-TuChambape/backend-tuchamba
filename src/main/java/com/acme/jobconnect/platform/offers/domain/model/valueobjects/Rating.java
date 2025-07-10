package com.acme.jobconnect.platform.offers.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Rating {
    private Integer value;

    public Rating() {
    }

    public Rating(Integer value) {
        if (value <= 0 || value > 5) {
            throw new IllegalArgumentException("Rating must be greater than 0 and less than or equal to 5");
        }
        this.value = value;
    }
} 