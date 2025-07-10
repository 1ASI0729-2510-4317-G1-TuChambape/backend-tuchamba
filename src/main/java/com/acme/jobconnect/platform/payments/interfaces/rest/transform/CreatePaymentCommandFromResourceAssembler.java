package com.acme.jobconnect.platform.payments.interfaces.rest.transform;

import com.acme.jobconnect.platform.payments.domain.model.commands.CreatePaymentCommand;
import com.acme.jobconnect.platform.payments.interfaces.rest.resources.CreatePaymentResource;

public class CreatePaymentCommandFromResourceAssembler {
    public static CreatePaymentCommand toCommandFromResource(CreatePaymentResource resource) {
        return new CreatePaymentCommand(
                resource.offerId(),
                resource.amount(),
                resource.currency()
        );
    }
} 