package com.acme.jobconnect.platform.payments.interfaces.rest.resources;

import java.math.BigDecimal;

public record PaymentResource(
        Long id,
        Long offerId,
        BigDecimal amount,
        String currency,
        String status,
        boolean workerVerified,
        boolean customerVerified,
        String createdAt,
        String updatedAt
) {
} 