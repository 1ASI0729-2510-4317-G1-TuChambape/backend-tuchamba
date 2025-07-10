package com.acme.jobconnect.platform.offers.interfaces.rest.resources;

public record UpdateReviewResource(
        Integer rating,
        String comment
) {
} 