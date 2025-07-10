package com.acme.jobconnect.platform.users.interfaces.rest.resources;

import java.util.List;

public record PreferencesResource(
        List<String> skills,
        List<String> tools,
        List<String> jobModality,
        List<String> jobExperience
) {
} 