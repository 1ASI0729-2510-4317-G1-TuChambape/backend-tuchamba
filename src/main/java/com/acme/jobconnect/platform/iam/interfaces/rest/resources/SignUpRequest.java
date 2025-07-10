package com.acme.jobconnect.platform.iam.interfaces.rest.resources;

import java.util.List;

public record SignUpRequest(String name, String email, String password, List<String> roles) {
} 