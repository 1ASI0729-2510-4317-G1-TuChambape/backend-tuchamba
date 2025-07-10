package com.acme.jobconnect.platform.payments.domain.services;

import com.acme.jobconnect.platform.payments.domain.model.aggregates.Payment;
import com.acme.jobconnect.platform.payments.domain.model.queries.GetAllPaymentsQuery;
import com.acme.jobconnect.platform.payments.domain.model.queries.GetPaymentByIdQuery;
import com.acme.jobconnect.platform.payments.domain.model.queries.GetPaymentByOfferIdQuery;

import java.util.List;
import java.util.Optional;

public interface PaymentQueryService {
    Optional<Payment> handle(GetPaymentByIdQuery query);
    Optional<Payment> handle(GetPaymentByOfferIdQuery query);
    List<Payment> handle(GetAllPaymentsQuery query);
} 