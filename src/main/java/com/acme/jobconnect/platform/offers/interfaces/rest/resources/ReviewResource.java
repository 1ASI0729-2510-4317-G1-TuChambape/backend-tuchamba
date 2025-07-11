package com.acme.jobconnect.platform.offers.interfaces.rest.resources;

import java.util.Date;

public record ReviewResource(
        Long id,
        Long offerId,
        Long authorUserId,
        Long reviewerUserId,
        String authorName,
        String authorImageUrl,
        Date createdAt,
        boolean isVerifiedAuthor,
        Integer rating,
        String comment
) {
} 