package com.acme.jobconnect.platform.users.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class WorkerRating {
    private Double rating;

    public WorkerRating() {
        this.rating = 0.0;
    }

    public WorkerRating(Double rating) {
        if (rating < 0 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 0 and 5");
        }
        this.rating = rating;
    }
} 