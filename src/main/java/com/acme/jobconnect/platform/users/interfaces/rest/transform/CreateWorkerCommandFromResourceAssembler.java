package com.acme.jobconnect.platform.users.interfaces.rest.transform;

import com.acme.jobconnect.platform.users.domain.model.commands.CreateWorkerProfileCommand;
import com.acme.jobconnect.platform.users.interfaces.rest.resources.CreateWorkerResource;

public class CreateWorkerCommandFromResourceAssembler {
    public static CreateWorkerProfileCommand toCommandFromResource(CreateWorkerResource resource) {
        return new CreateWorkerProfileCommand(
                resource.accountId(),
                resource.email(),
                resource.firstName(),
                resource.lastName(),
                resource.phone(),
                resource.avatar(),
                resource.location(),
                resource.bio(),
                resource.skills(),
                resource.experience(),
                resource.certifications(),
                resource.availability().monday(),
                resource.availability().tuesday(),
                resource.availability().wednesday(),
                resource.availability().thursday(),
                resource.availability().friday(),
                resource.availability().saturday(),
                resource.availability().sunday(),
                resource.yapeNumber(),
                resource.plinNumber(),
                resource.bankAccountNumber()
        );
    }
} 