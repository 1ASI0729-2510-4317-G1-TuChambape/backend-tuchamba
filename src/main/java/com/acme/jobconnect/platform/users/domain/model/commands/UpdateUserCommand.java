package com.acme.jobconnect.platform.users.domain.model.commands;

public record UpdateUserCommand(
        Long id,
        Long workerId,
        Long customerId
) {
} 