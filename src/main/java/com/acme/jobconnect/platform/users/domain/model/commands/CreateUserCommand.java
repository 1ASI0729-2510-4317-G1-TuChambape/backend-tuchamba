package com.acme.jobconnect.platform.users.domain.model.commands;

public record CreateUserCommand(
        Long accountId,
        Long workerId,
        Long customerId
) {
} 