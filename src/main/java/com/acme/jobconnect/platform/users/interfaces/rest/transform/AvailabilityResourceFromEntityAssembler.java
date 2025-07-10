package com.acme.jobconnect.platform.users.interfaces.rest.transform;

import com.acme.jobconnect.platform.users.domain.model.entities.Availability;
import com.acme.jobconnect.platform.users.interfaces.rest.resources.AvailabilityResource;

public class AvailabilityResourceFromEntityAssembler {
    public static AvailabilityResource toResourceFromEntity(Availability entity) {
        if (entity == null) {
            return null;
        }
        return new AvailabilityResource(
                entity.getMonday(),
                entity.getTuesday(),
                entity.getWednesday(),
                entity.getThursday(),
                entity.getFriday(),
                entity.getSaturday(),
                entity.getSunday()
        );
    }
} 