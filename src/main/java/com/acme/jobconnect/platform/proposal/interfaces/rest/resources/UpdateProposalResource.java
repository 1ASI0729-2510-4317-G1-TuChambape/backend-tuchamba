package com.acme.jobconnect.platform.proposal.interfaces.rest.resources;

import java.math.BigDecimal;

public record UpdateProposalResource(
        String message,
        double price
) {
} 