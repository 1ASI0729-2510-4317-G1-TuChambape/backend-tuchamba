package com.acme.jobconnect.platform.users.interfaces.rest.resources;

public record CustomerResource(
        Long id,
        String email,
        String firstName,
        String lastName,
        String phone,
        String location,
        String bio,
        boolean isVerified,
        PreferencesResource preferences
) {
} 