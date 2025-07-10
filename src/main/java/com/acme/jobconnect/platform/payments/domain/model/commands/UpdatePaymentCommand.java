package com.acme.jobconnect.platform.payments.domain.model.commands;

import java.math.BigDecimal;

public record UpdatePaymentCommand(
        Long id,
        BigDecimal amount,
        String currency
) {
} 