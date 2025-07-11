package com.acme.jobconnect.platform.users.interfaces.rest.resources;

public record UpdateUserResource(
        Long workerId,
        Long customerId
) {
} 