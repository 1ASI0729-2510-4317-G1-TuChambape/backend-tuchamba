package com.acme.jobconnect.platform.proposal.interfaces.rest.resources;

import java.math.BigDecimal;

public record ProposalResource(
        Long id,
        Long offerId,
        Long workerId,
        String message,
        String status,
        double price
) {
} 