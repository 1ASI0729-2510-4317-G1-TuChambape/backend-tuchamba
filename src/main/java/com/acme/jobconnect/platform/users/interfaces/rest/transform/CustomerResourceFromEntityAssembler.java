package com.acme.jobconnect.platform.users.interfaces.rest.transform;

import com.acme.jobconnect.platform.users.domain.model.aggregates.Customer;
import com.acme.jobconnect.platform.users.interfaces.rest.resources.CustomerResource;

public class CustomerResourceFromEntityAssembler {
    public static CustomerResource toResourceFromEntity(Customer entity) {
        if (entity == null) {
            return null;
        }
        var preferencesResource = PreferencesResourceFromEntityAssembler.toResourceFromEntity(entity.getPreferences());
        return new CustomerResource(
                entity.getId(),
                entity.getEmail(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getPhone(),
                entity.getLocation(),
                entity.getBio(),
                entity.isVerified(),
                preferencesResource
        );
    }
} 