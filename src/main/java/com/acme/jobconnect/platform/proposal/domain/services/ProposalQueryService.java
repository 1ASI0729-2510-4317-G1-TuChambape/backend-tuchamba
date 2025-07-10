package com.acme.jobconnect.platform.proposal.domain.services;

import com.acme.jobconnect.platform.proposal.domain.model.aggregates.Proposal;
import com.acme.jobconnect.platform.proposal.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface ProposalQueryService {
    List<Proposal> handle(GetAllProposalByOfferIdQuery query);
    List<Proposal> handle(GetAllProposalByStatusQuery query);
    List<Proposal> handle(GetAllProposalByWorkerIdQuery query);
    List<Proposal> handle(GetAllProposalsQuery query);
    Optional<Proposal> handle(GetProposalByIdQuery query);
}
