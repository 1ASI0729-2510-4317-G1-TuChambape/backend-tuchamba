package com.acme.jobconnect.platform.proposal.domain.model.queries;

import com.acme.jobconnect.platform.proposal.domain.model.valueobjects.ProposalStatus;

public record GetAllProposalByStatusQuery(ProposalStatus status) {
}
