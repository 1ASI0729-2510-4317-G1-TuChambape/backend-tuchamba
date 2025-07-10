package com.acme.jobconnect.platform.proposal.interfaces.rest.transform;

import com.acme.jobconnect.platform.proposal.domain.model.commands.CreateProposalCommand;
import com.acme.jobconnect.platform.proposal.interfaces.rest.resources.CreateProposalResource;

public class CreateProposalCommandFromResourceAssembler {
    public static CreateProposalCommand toCommandFromResource(CreateProposalResource resource) {
        return new CreateProposalCommand(
                resource.offerId(),
                resource.workerId(),
                resource.message(),
                "PENDING",
                resource.price()
        );
    }
} 