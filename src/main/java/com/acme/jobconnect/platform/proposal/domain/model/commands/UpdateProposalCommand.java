package com.acme.jobconnect.platform.proposal.domain.model.commands;

public record UpdateProposalCommand(
        Long id,
        String message,
        double price
) {
} 