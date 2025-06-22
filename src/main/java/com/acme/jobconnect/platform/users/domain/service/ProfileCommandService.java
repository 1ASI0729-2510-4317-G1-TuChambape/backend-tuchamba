package com.acme.jobconnect.platform.users.domain.service;

import com.acme.jobconnect.platform.users.domain.model.aggregates.User;
import com.acme.jobconnect.platform.users.interfaces.rest.resources.dto.UpdateProfileResource;

import java.util.Optional;

public interface ProfileCommandService {
    Optional<User> handle(Long userId, UpdateProfileResource resource);
}
