package com.acme.jobconnect.platform.offers.interfaces.rest.transform;

import com.acme.jobconnect.platform.offers.domain.model.entities.Review;
import com.acme.jobconnect.platform.offers.interfaces.rest.resources.ReviewResource;

public class ReviewResourceFromEntityAssembler {
    public static ReviewResource toResourceFromEntity(Review entity) {
        if (entity == null) {
            return null;
        }
        return new ReviewResource(
                entity.getId(),
                entity.getOffer().getId(),
                entity.getAuthorUserId(),
                entity.getReviewerUserId(),
                entity.getAuthorName(),
                entity.getAuthorImageUrl(),
                entity.getCreatedAt(),
                entity.isVerifiedAuthor(),
                entity.getRating().getValue(),
                entity.getComment()
        );
    }
} 