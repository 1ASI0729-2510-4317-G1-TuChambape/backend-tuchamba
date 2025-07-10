package com.acme.jobconnect.platform.offers.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Budget {

    private Integer min;
    private Integer max;
    private String currency;

    public Budget() {
        this.min = 0;
        this.max = 0;
        this.currency = "USD";
    }

    public Budget(Integer min, Integer max, String currency) {
        this.min = min;
        this.max = max;
        this.currency = currency;
    }
} 