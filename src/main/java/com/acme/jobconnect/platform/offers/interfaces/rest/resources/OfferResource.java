package com.acme.jobconnect.platform.offers.interfaces.rest.resources;

import com.acme.jobconnect.platform.offers.domain.model.valueobjects.Budget;

import java.util.Date;
import java.util.List;

public record OfferResource(
        Long id,
        String title,
        String description,
        Long clientId,
        String clientEmail,
        String status,
        String category,
        String location,
        Budget budget,
        List<String> requirements,
        Date createdAt,
        Date updatedAt,
        Date deadline,
        Integer proposalsCount,
        Long selectedProposalId,
        Date startAt
) {
} 