package com.acme.jobconnect.platform.users.domain.model.commands;

import java.util.List;

public record CreateWorkerProfileCommand(
        Long accountId,
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
) {} 