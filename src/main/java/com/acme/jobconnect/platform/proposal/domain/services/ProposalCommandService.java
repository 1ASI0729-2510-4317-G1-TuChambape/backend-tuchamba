package com.acme.jobconnect.platform.proposal.domain.services;

import com.acme.jobconnect.platform.proposal.domain.model.aggregates.Proposal;
import com.acme.jobconnect.platform.proposal.domain.model.commands.*;

import java.util.Optional;

public interface ProposalCommandService {
    Optional<Proposal> handle(CreateProposalCommand command);
    void handle(DeleteProposalByIdCommand command);
    Optional<Proposal> handle(UpdateProposalCommand command);
    Optional<Proposal> handle(AcceptProposalCommand command);
    Optional<Proposal> handle(RejectProposalCommand command);
}
