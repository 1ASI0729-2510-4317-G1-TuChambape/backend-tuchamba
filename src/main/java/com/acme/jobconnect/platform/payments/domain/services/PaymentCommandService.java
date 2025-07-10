package com.acme.jobconnect.platform.payments.domain.services;

import com.acme.jobconnect.platform.payments.domain.model.commands.*;

import java.util.Optional;

public interface PaymentCommandService {
    Optional<Long> handle(CreatePaymentCommand command);
    Optional<Long> handle(UpdatePaymentCommand command);
    void handle(DeletePaymentCommand command);
    Optional<Long> handle(VerifyPaymentByWorkerCommand command);
    Optional<Long> handle(VerifyPaymentByCustomerCommand command);
    Optional<Long> handle(ConfirmPaymentCommand command);
} 