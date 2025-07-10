package com.acme.jobconnect.platform.users.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class EstimatedBudgetRange {
    private Integer min;
    private Integer max;

    public EstimatedBudgetRange() {
        this.min = 0;
        this.max = 0;
    }

    public EstimatedBudgetRange(Integer min, Integer max) {
        if (min < 0) {
            throw new IllegalArgumentException("Minimum budget cannot be negative");
        }
        if (max > 100000) {
            throw new IllegalArgumentException("Maximum budget cannot exceed 100,000");
        }
        if (min > max) {
            throw new IllegalArgumentException("Minimum budget cannot be greater than maximum budget");
        }
        this.min = min;
        this.max = max;
    }
} 