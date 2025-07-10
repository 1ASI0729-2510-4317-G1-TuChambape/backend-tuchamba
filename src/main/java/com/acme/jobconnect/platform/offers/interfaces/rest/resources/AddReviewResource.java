package com.acme.jobconnect.platform.offers.interfaces.rest.resources;

public record AddReviewResource(
        Long authorUserId,
        Long reviewerUserId,
        String authorName,
        String authorImageUrl,
        boolean isVerifiedAuthor,
        Integer rating,
        String comment
) {
} 