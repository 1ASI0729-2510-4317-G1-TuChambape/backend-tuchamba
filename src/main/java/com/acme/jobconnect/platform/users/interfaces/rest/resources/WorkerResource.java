package com.acme.jobconnect.platform.users.interfaces.rest.resources;

import java.util.List;

public record WorkerResource(
        Long id,
        String email,
        String firstName,
        String lastName,
        String phone,
        String avatar,
        String location,
        String bio,
        List<String> skills,
        Integer experience,
        List<String> certifications,
        Double rating,
        Integer reviewCount,
        boolean isVerified,
        AvailabilityResource availability,
        String yapeNumber,
        String plinNumber,
        String bankAccountNumber
) {
} 