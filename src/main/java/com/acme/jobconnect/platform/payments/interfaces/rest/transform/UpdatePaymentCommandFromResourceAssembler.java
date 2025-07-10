package com.acme.jobconnect.platform.payments.interfaces.rest.transform;

import com.acme.jobconnect.platform.payments.domain.model.commands.UpdatePaymentCommand;
import com.acme.jobconnect.platform.payments.interfaces.rest.resources.UpdatePaymentResource;

public class UpdatePaymentCommandFromResourceAssembler {
    public static UpdatePaymentCommand toCommandFromResource(Long id, UpdatePaymentResource resource) {
        return new UpdatePaymentCommand(
                id,
                resource.amount(),
                resource.currency()
        );
    }
} 