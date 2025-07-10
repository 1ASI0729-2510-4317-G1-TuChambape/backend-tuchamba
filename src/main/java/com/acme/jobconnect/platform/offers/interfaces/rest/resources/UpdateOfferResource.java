package com.acme.jobconnect.platform.offers.interfaces.rest.resources;

import com.acme.jobconnect.platform.offers.domain.model.valueobjects.OfferStatus;
import com.acme.jobconnect.platform.offers.domain.model.valueobjects.Budget;

import java.util.Date;
import java.util.List;

public record UpdateOfferResource(
        String title,
        String description,
        OfferStatus status,
        String category,
        String location,
        Budget budget,
        List<String> requirements,
        Date deadline,
        Date startAt
) {
    public UpdateOfferResource {
        if (title != null && title.isBlank()) throw new IllegalArgumentException("Title cannot be blank");
        if (description != null && description.isBlank()) throw new IllegalArgumentException("Description cannot be blank");
        if (category != null && category.isBlank()) throw new IllegalArgumentException("Category cannot be blank");
        if (location != null && location.isBlank()) throw new IllegalArgumentException("Location cannot be blank");
        if (startAt != null && deadline != null && startAt.after(deadline)) {
            throw new IllegalArgumentException("Start date cannot be after deadline");
        }
    }
} 