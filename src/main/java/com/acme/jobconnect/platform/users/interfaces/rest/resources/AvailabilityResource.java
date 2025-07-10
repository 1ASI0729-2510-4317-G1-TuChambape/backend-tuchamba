package com.acme.jobconnect.platform.users.interfaces.rest.resources;

public record AvailabilityResource(
        String monday,
        String tuesday,
        String wednesday,
        String thursday,
        String friday,
        String saturday,
        String sunday
) {
} 