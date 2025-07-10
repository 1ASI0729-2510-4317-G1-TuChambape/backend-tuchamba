package com.acme.jobconnect.platform.proposal.interfaces.rest.resources;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreateProposalResource(
        Long offerId,
        Long workerId,
        String message,
        @NotNull
        double price
) {
} 