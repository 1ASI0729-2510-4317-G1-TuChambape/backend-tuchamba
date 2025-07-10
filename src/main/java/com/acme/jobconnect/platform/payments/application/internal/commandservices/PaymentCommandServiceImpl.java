package com.acme.jobconnect.platform.payments.application.internal.commandservices;

import com.acme.jobconnect.platform.payments.domain.model.aggregates.Payment;
import com.acme.jobconnect.platform.payments.domain.model.commands.*;
import com.acme.jobconnect.platform.payments.domain.services.PaymentCommandService;
import com.acme.jobconnect.platform.payments.infrastructure.persistence.jpa.repositories.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentCommandServiceImpl implements PaymentCommandService {

    private final PaymentRepository paymentRepository;

    @Override
    public Optional<Long> handle(CreatePaymentCommand command) {
        if (paymentRepository.findByOfferId(command.offerId()).isPresent()) {
            throw new IllegalArgumentException("Payment for this offer already exists");
        }
        var payment = new Payment(command.offerId(), command.amount(), command.currency());
        paymentRepository.save(payment);
        return Optional.of(payment.getId());
    }

    @Override
    public Optional<Long> handle(UpdatePaymentCommand command) {
        var payment = paymentRepository.findById(command.id())
                .orElseThrow(() -> new IllegalArgumentException("Payment with id " + command.id() + " not found"));
        payment.setAmount(command.amount());
        payment.setCurrency(command.currency());
        paymentRepository.save(payment);
        return Optional.of(payment.getId());
    }

    @Override
    public void handle(DeletePaymentCommand command) {
        if (!paymentRepository.existsById(command.id())) {
            throw new IllegalArgumentException("Payment with id " + command.id() + " not found");
        }
        paymentRepository.deleteById(command.id());
    }

    @Override
    public Optional<Long> handle(VerifyPaymentByWorkerCommand command) {
        var payment = paymentRepository.findById(command.id())
                .orElseThrow(() -> new IllegalArgumentException("Payment with id " + command.id() + " not found"));
        payment.setWorkerVerified(true);
        paymentRepository.save(payment);
        return Optional.of(payment.getId());
    }

    @Override
    public Optional<Long> handle(VerifyPaymentByCustomerCommand command) {
        var payment = paymentRepository.findById(command.id())
                .orElseThrow(() -> new IllegalArgumentException("Payment with id " + command.id() + " not found"));
        payment.setCustomerVerified(true);
        paymentRepository.save(payment);
        return Optional.of(payment.getId());
    }

    @Override
    public Optional<Long> handle(ConfirmPaymentCommand command) {
        var payment = paymentRepository.findById(command.id())
                .orElseThrow(() -> new IllegalArgumentException("Payment with id " + command.id() + " not found"));
        payment.confirmPayment();
        paymentRepository.save(payment);
        return Optional.of(payment.getId());
    }
} 