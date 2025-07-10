package com.acme.jobconnect.platform.payments.interfaces.rest.resources;

import java.math.BigDecimal;

public record UpdatePaymentResource(
        BigDecimal amount,
        String currency
) {
} 