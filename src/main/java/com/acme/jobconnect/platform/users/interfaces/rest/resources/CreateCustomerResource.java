package com.acme.jobconnect.platform.users.interfaces.rest.resources;

public record CreateCustomerResource(
        Long accountId,
        String email,
        String firstName,
        String lastName,
        String phone,
        String location,
        String bio
) {
} 