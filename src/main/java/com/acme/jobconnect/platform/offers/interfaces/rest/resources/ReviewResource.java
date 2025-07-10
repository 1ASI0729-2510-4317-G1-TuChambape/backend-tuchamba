package com.acme.jobconnect.platform.offers.interfaces.rest.resources;

public record ReviewResource(
        Long id,
        Long offerId,
        Long authorUserId,
        Long reviewerUserId,
        String authorName,
        String authorImageUrl,
        String createdAt,
        boolean isVerifiedAuthor,
        Integer rating,
        String comment
) {
} 