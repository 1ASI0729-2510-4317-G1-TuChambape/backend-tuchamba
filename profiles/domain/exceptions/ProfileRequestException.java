package com.acme.jobconnect.platform.profiles.domain.exceptions;

public class ProfileRequestException extends RuntimeException {
    public ProfileRequestException(String message) {
        super("Error while creating the profile: %s".formatted(message));
    }
}
