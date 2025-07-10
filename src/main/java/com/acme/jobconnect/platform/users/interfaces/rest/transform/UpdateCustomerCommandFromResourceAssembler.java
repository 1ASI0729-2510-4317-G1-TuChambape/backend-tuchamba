package com.acme.jobconnect.platform.users.interfaces.rest.transform;

import com.acme.jobconnect.platform.users.domain.model.commands.UpdateCustomerCommand;
import com.acme.jobconnect.platform.users.interfaces.rest.resources.UpdateCustomerResource;

public class UpdateCustomerCommandFromResourceAssembler {
    public static UpdateCustomerCommand toCommandFromResource(Long id, UpdateCustomerResource resource) {
        return new UpdateCustomerCommand(
                id,
                resource.firstName(),
                resource.lastName(),
                resource.phone(),
                resource.location(),
                resource.bio()
        );
    }
} 