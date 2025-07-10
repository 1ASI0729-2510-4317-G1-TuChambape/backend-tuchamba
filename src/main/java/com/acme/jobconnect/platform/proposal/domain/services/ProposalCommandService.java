package com.acme.jobconnect.platform.proposal.domain.services;

import com.acme.jobconnect.platform.proposal.domain.model.aggregates.Proposal;
import com.acme.jobconnect.platform.proposal.domain.model.commands.CreateProposalCommand;
import com.acme.jobconnect.platform.proposal.domain.model.commands.DeleteProposalByIdCommand;

import java.util.Optional;

public interface ProposalCommandService {
    Optional<Proposal> handle(CreateProposalCommand command);
    void handle(DeleteProposalByIdCommand command);
}
