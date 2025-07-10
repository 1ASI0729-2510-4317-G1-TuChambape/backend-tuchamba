package com.acme.jobconnect.platform.payments.infrastructure.persistence.jpa.repositories;

import com.acme.jobconnect.platform.payments.domain.model.aggregates.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByOfferId(Long offerId);
} 