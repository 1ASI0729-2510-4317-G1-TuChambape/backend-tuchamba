package com.acme.jobconnect.platform.users.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import java.util.Arrays;

@Embeddable
public record PreferredAvailability(String availability) {

    private enum AvailabilityValues {
        FULL_TIME,
        PART_TIME,
        PROJECT_BASED,
        HOURLY
    }

    public PreferredAvailability {
        if (availability == null || availability.isBlank()) {
            throw new IllegalArgumentException("Availability cannot be null or empty");
        }
        try {
            AvailabilityValues.valueOf(availability.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid availability value. Must be one of: " +
                    Arrays.toString(AvailabilityValues.values()));
        }
    }

    public PreferredAvailability() {
        this(null);
    }
}
