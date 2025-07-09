package com.acme.jobconnect.platform.users.interfaces.rest.resources.dto;

import java.util.Set;

public record SignUpRequest(
        String firstName,
        String lastName,
        String email,
        String password,
        Set<String> roles
) {
}