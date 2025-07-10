package com.acme.jobconnect.platform.users.interfaces.rest.resources;

public record CreateUserResource(
        Long accountId,
        Long workerId,
        Long customerId
) {
} 