package com.acme.jobconnect.platform.proposal.domain.model.commands;

import java.math.BigDecimal;

public record CreateProposalCommand(
        Long offerId,
        Long workerId,
        String message,
        String status,
        double price
) {
}
