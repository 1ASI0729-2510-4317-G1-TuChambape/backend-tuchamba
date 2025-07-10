package com.acme.jobconnect.platform.offers.domain.model.commands;

public record UpdateReviewCommand(
        Long id,
        Integer rating,
        String comment
) {
} 