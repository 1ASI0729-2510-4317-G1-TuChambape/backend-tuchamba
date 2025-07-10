package com.acme.jobconnect.platform.proposal.interfaces.rest.transform;

import com.acme.jobconnect.platform.proposal.domain.model.commands.UpdateProposalCommand;
import com.acme.jobconnect.platform.proposal.interfaces.rest.resources.UpdateProposalResource;

public class UpdateProposalCommandFromResourceAssembler {
    public static UpdateProposalCommand toCommandFromResource(Long id, UpdateProposalResource resource) {
        return new UpdateProposalCommand(
                id,
                resource.message(),
                resource.price()
        );
    }
} 