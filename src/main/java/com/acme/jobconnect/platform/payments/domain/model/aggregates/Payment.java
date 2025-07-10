package com.acme.jobconnect.platform.payments.domain.model.aggregates;

import com.acme.jobconnect.platform.payments.domain.model.valueobjects.PaymentStatus;
import com.acme.jobconnect.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "payments")
@Getter
public class Payment extends AuditableAbstractAggregateRoot<Payment> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long offerId;

    @NotNull
    @Setter
    private BigDecimal amount;

    @NotNull
    @Setter
    private String currency;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Setter
    private PaymentStatus status;

    @Setter
    private boolean workerVerified = false;

    @Setter
    private boolean customerVerified = false;

    public Payment() {
    }

    public Payment(Long offerId, BigDecimal amount, String currency) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }
        this.offerId = offerId;
        this.amount = amount;
        this.currency = currency;
        this.status = PaymentStatus.PENDING;
    }

    public void confirmPayment() {
        if (this.workerVerified && this.customerVerified) {
            this.status = PaymentStatus.PAID;
        } else {
            throw new IllegalStateException("Both worker and customer must verify the payment before it can be confirmed.");
        }
    }
} 