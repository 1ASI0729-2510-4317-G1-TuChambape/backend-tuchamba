package com.acme.jobconnect.platform.users.interfaces.rest.transform;

import com.acme.jobconnect.platform.users.domain.model.aggregates.Worker;
import com.acme.jobconnect.platform.users.interfaces.rest.resources.WorkerResource;

public class WorkerResourceFromEntityAssembler {
    public static WorkerResource toResourceFromEntity(Worker entity) {
        if (entity == null) {
            return null;
        }

        var availabilityResource = AvailabilityResourceFromEntityAssembler.toResourceFromEntity(entity.getAvailability());

        return new WorkerResource(
                entity.getId(),
                entity.getEmail(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getPhone(),
                entity.getAvatar(),
                entity.getLocation(),
                entity.getBio(),
                entity.getSkills(),
                entity.getExperience(),
                entity.getCertifications(),
                entity.getRating() != null ? entity.getRating().getRating() : null,
                entity.getReviewCount(),
                entity.isVerified(),
                availabilityResource,
                entity.getYapeNumber(),
                entity.getPlinNumber(),
                entity.getBankAccountNumber()
        );
    }
} 