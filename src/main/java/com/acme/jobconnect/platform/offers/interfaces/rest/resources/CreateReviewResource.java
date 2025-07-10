package com.acme.jobconnect.platform.offers.interfaces.rest.resources;

public record CreateReviewResource(
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