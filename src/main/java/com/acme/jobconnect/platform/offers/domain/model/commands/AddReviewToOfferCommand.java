package com.acme.jobconnect.platform.offers.domain.model.commands;

public record AddReviewToOfferCommand(
        Long offerId,
        Long authorUserId,
        Long reviewerUserId,
        String authorName,
        String authorImageUrl,
        boolean isVerifiedAuthor,
        Integer rating,
        String comment
) {
} 