package com.acme.jobconnect.platform.users.domain.model.commands;

import java.util.Set;

public record SignUpCommand(
        String firstName,
        String lastName,
        String email,
        String password,
        Set<String> roles
) {
}