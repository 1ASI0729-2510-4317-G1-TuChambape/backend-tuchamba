package com.acme.jobconnect.platform.users.domain.model.commands;

public record CreateCustomerProfileCommand(
        Long accountId,
        String email,
        String firstName,
        String lastName,
        String phone,
        String location,
        String bio
) {} 