package com.acme.jobconnect.platform.users.interfaces.rest.transform;

import com.acme.jobconnect.platform.users.domain.model.commands.CreateCustomerProfileCommand;
import com.acme.jobconnect.platform.users.interfaces.rest.resources.CreateCustomerResource;

public class CreateCustomerCommandFromResourceAssembler {
    public static CreateCustomerProfileCommand toCommandFromResource(CreateCustomerResource resource) {
        return new CreateCustomerProfileCommand(
                resource.accountId(),
                resource.email(),
                resource.firstName(),
                resource.lastName(),
                resource.phone(),
                resource.location(),
                resource.bio()
        );
    }
} 