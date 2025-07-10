package com.acme.jobconnect.platform.offers.interfaces.rest.transform;

import com.acme.jobconnect.platform.offers.domain.model.commands.UpdateReviewCommand;
import com.acme.jobconnect.platform.offers.interfaces.rest.resources.UpdateReviewResource;

public class UpdateReviewCommandFromResourceAssembler {
    public static UpdateReviewCommand toCommandFromResource(Long id, UpdateReviewResource resource) {
        return new UpdateReviewCommand(
                id,
                resource.rating(),
                resource.comment()
        );
    }
} 