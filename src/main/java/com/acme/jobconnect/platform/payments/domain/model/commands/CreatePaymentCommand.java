package com.acme.jobconnect.platform.payments.domain.model.commands;

import java.math.BigDecimal;

public record CreatePaymentCommand(
        Long offerId,
        BigDecimal amount,
        String currency
) {
} 