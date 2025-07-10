package com.acme.jobconnect.platform.users.interfaces.rest.resources;

public record UpdateCustomerResource(
        String firstName,
        String lastName,
        String phone,
        String location,
        String bio
) {
} 