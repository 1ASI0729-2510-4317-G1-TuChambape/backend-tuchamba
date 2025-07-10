package com.acme.jobconnect.platform.proposal.domain.model.commands;


public record CreateProposalCommand(
        Long offerId,
        Long workerId,
        String message,
        double price,
        String status
) {}
