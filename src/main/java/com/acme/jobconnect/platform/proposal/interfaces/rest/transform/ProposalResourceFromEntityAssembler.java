package com.acme.jobconnect.platform.proposal.interfaces.rest.transform;

import com.acme.jobconnect.platform.proposal.domain.model.aggregates.Proposal;
import com.acme.jobconnect.platform.proposal.interfaces.rest.resources.ProposalResource;

public class ProposalResourceFromEntityAssembler {
    public static ProposalResource toResourceFromEntity(Proposal entity) {
        return new ProposalResource(
                entity.getId(),
                entity.getOffer().getId(),
                entity.getWorker().getId(),
                entity.getMessage(),
                entity.getStatus().name(),
                entity.getPrice().price()
        );
    }
} 