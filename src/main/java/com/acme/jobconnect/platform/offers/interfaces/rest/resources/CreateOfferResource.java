package com.acme.jobconnect.platform.offers.interfaces.rest.resources;

import com.acme.jobconnect.platform.offers.domain.model.valueobjects.Budget;

import java.util.Date;
import java.util.List;

public record CreateOfferResource(
        String title,
        String description,
        Long clientId,
        String clientEmail,
        String category,
        String location,
        Budget budget,
        List<String> requirements,
        Date deadline,
        Date startAt
) {
    public CreateOfferResource {
        if (title == null || title.isBlank()) throw new IllegalArgumentException("Title cannot be null or empty");
        if (description == null || description.isBlank()) throw new IllegalArgumentException("Description cannot be null or empty");
        if (clientId == null) throw new IllegalArgumentException("Client ID cannot be null");
        if (clientEmail == null || clientEmail.isBlank()) throw new IllegalArgumentException("Client email cannot be null or empty");
        if (category == null || category.isBlank()) throw new IllegalArgumentException("Category cannot be null or empty");
        if (location == null || location.isBlank()) throw new IllegalArgumentException("Location cannot be null or empty");
        if (budget == null) throw new IllegalArgumentException("Budget cannot be null");
        if (startAt != null && deadline != null && startAt.after(deadline)) {
            throw new IllegalArgumentException("Start date cannot be after deadline");
        }
    }
} 