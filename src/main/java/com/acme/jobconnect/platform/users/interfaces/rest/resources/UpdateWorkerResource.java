package com.acme.jobconnect.platform.users.interfaces.rest.resources;

import java.util.List;

public record UpdateWorkerResource(
        String firstName,
        String lastName,
        String phone,
        String avatar,
        String location,
        String bio,
        List<String> skills,
        Integer experience,
        List<String> certifications,
        String monday,
        String tuesday,
        String wednesday,
        String thursday,
        String friday,
        String saturday,
        String sunday,
        String yapeNumber,
        String plinNumber,
        String bankAccountNumber
) {
} 