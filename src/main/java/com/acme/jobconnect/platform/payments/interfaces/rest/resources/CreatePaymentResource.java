package com.acme.jobconnect.platform.payments.interfaces.rest.resources;

import java.math.BigDecimal;

public record CreatePaymentResource(
        Long offerId,
        BigDecimal amount,
        String currency
) {
} 