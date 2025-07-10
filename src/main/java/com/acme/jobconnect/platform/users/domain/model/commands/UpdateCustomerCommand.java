package com.acme.jobconnect.platform.users.domain.model.commands;

public record UpdateCustomerCommand(
        Long id,
        String firstName,
        String lastName,
        String phone,
        String location,
        String bio
) {
} 