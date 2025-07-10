package com.acme.jobconnect.platform.offers.interfaces.rest.transform;

import com.acme.jobconnect.platform.offers.domain.model.commands.AddReviewToOfferCommand;
import com.acme.jobconnect.platform.offers.interfaces.rest.resources.AddReviewResource;

/**
 * Assembler to convert an {@link AddReviewResource} to an {@link AddReviewToOfferCommand}.
 */
public class AddReviewCommandFromResourceAssembler {
    /**
     * Converts an {@link AddReviewResource} to an {@link AddReviewToOfferCommand}.
     * @param offerId The offer id.
     * @param resource The resource to convert.
     * @return The command.
     */
    public static AddReviewToOfferCommand toCommandFromResource(Long offerId, AddReviewResource resource) {
        return new AddReviewToOfferCommand(
                offerId,
                resource.authorUserId(),
                resource.reviewerUserId(),
                resource.authorName(),
                resource.authorImageUrl(),
                resource.isVerifiedAuthor(),
                resource.rating(),
                resource.comment()
        );
    }
} 