package com.acme.jobconnect.platform.users.interfaces.rest.transform;

import com.acme.jobconnect.platform.users.domain.model.commands.CreateUserCommand;
import com.acme.jobconnect.platform.users.interfaces.rest.resources.CreateUserResource;

public class CreateUserCommandFromResourceAssembler {
    public static CreateUserCommand toCommandFromResource(CreateUserResource resource) {
        return new CreateUserCommand(
                resource.accountId(),
                resource.workerId(),
                resource.customerId()
        );
    }
} 