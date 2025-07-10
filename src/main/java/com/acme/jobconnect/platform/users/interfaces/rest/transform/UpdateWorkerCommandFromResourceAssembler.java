package com.acme.jobconnect.platform.users.interfaces.rest.transform;

import com.acme.jobconnect.platform.users.domain.model.commands.UpdateWorkerCommand;
import com.acme.jobconnect.platform.users.interfaces.rest.resources.UpdateWorkerResource;

public class UpdateWorkerCommandFromResourceAssembler {
    public static UpdateWorkerCommand toCommandFromResource(Long id, UpdateWorkerResource resource) {
        return new UpdateWorkerCommand(
                id,
                resource.firstName(),
                resource.lastName(),
                resource.phone(),
                resource.avatar(),
                resource.location(),
                resource.bio(),
                resource.skills(),
                resource.experience(),
                resource.certifications(),
                resource.monday(),
                resource.tuesday(),
                resource.wednesday(),
                resource.thursday(),
                resource.friday(),
                resource.saturday(),
                resource.sunday(),
                resource.yapeNumber(),
                resource.plinNumber(),
                resource.bankAccountNumber()
        );
    }
} 