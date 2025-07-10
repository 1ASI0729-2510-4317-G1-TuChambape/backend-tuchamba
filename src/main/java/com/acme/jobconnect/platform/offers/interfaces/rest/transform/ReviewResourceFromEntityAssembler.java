package com.acme.jobconnect.platform.offers.interfaces.rest.transform;

import com.acme.jobconnect.platform.offers.domain.model.entities.Review;
import com.acme.jobconnect.platform.offers.interfaces.rest.resources.ReviewResource;

import java.text.SimpleDateFormat;

/**
 * Assembler to convert a {@link Review} entity to a {@link ReviewResource}.
 */
public class ReviewResourceFromEntityAssembler {
    /**
     * Converts a {@link Review} entity to a {@link ReviewResource}.
     * @param entity The entity to convert.
     * @return The resource.
     */
    public static ReviewResource toResourceFromEntity(Review entity) {
        return new ReviewResource(
                entity.getId(),
                entity.getOffer().getId(),
                entity.getAuthorUserId(),
                entity.getReviewerUserId(),
                entity.getAuthorName(),
                entity.getAuthorImageUrl(),
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").format(entity.getCreatedAt()),
                entity.isVerifiedAuthor(),
                entity.getRating().getValue(),
                entity.getComment()
        );
    }
} 