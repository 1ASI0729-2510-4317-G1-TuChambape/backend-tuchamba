package com.acme.jobconnect.platform.users.interfaces.rest.resources;

public record UserResource(
        Long id,
        Long accountId,
        WorkerResource worker,
        CustomerResource customer
) {
} 