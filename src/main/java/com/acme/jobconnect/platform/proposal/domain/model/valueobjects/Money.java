package com.acme.jobconnect.platform.proposal.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Money(double price) {
    public Money() {this(0.0);}

    public Money {
        if (price < 0.0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
    }
}
