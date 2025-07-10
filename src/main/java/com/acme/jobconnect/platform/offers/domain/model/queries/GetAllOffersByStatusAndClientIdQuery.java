package com.acme.jobconnect.platform.offers.domain.model.queries;

public record GetAllOffersByStatusAndClientIdQuery(String status, Long clientId) {
} 