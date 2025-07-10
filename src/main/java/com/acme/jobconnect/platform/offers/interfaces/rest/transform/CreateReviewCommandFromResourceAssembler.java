package com.acme.jobconnect.platform.offers.interfaces.rest.transform;

import com.acme.jobconnect.platform.offers.domain.model.commands.CreateReviewCommand;
import com.acme.jobconnect.platform.offers.interfaces.rest.resources.CreateReviewResource;

public class CreateReviewCommandFromResourceAssembler {
    public static CreateReviewCommand toCommandFromResource(CreateReviewResource resource) {
        return new CreateReviewCommand(
                resource.offerId(),
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