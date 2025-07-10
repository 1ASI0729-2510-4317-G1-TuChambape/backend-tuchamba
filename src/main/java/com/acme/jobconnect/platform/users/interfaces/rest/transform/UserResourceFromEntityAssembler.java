package com.acme.jobconnect.platform.users.interfaces.rest.transform;

import com.acme.jobconnect.platform.users.domain.model.aggregates.User;
import com.acme.jobconnect.platform.users.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User entity) {
        return new UserResource(
                entity.getId(),
                entity.getAccountId(),
                entity.getWorker() != null ? WorkerResourceFromEntityAssembler.toResourceFromEntity(entity.getWorker()) : null,
                entity.getCustomer() != null ? CustomerResourceFromEntityAssembler.toResourceFromEntity(entity.getCustomer()) : null
        );
    }
} 