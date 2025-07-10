package com.acme.jobconnect.platform.iam.interfaces.rest.resources;

import java.util.List;

public record AccountResource(Long id, String name, String email, List<String> roles) {
} 