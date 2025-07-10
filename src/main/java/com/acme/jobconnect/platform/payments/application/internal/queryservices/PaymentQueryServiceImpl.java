package com.acme.jobconnect.platform.payments.application.internal.queryservices;

import com.acme.jobconnect.platform.payments.domain.model.aggregates.Payment;
import com.acme.jobconnect.platform.payments.domain.model.queries.GetAllPaymentsQuery;
import com.acme.jobconnect.platform.payments.domain.model.queries.GetPaymentByIdQuery;
import com.acme.jobconnect.platform.payments.domain.model.queries.GetPaymentByOfferIdQuery;
import com.acme.jobconnect.platform.payments.domain.services.PaymentQueryService;
import com.acme.jobconnect.platform.payments.infrastructure.persistence.jpa.repositories.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentQueryServiceImpl implements PaymentQueryService {

    private final PaymentRepository paymentRepository;

    @Override
    public Optional<Payment> handle(GetPaymentByIdQuery query) {
        return paymentRepository.findById(query.id());
    }

    @Override
    public Optional<Payment> handle(GetPaymentByOfferIdQuery query) {
        return paymentRepository.findByOfferId(query.offerId());
    }

    @Override
    public List<Payment> handle(GetAllPaymentsQuery query) {
        return paymentRepository.findAll();
    }
} 