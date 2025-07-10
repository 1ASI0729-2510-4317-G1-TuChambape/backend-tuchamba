package com.acme.jobconnect.platform.payments.interfaces.rest.transform;

import com.acme.jobconnect.platform.payments.domain.model.aggregates.Payment;
import com.acme.jobconnect.platform.payments.interfaces.rest.resources.PaymentResource;

public class PaymentResourceFromEntityAssembler {
    public static PaymentResource toResourceFromEntity(Payment entity) {
        if (entity == null) {
            return null;
        }
        return new PaymentResource(
                entity.getId(),
                entity.getOfferId(),
                entity.getAmount(),
                entity.getCurrency(),
                entity.getStatus().name(),
                entity.isWorkerVerified(),
                entity.isCustomerVerified(),
                entity.getCreatedAt().toString(),
                entity.getUpdatedAt().toString()
        );
    }
} 