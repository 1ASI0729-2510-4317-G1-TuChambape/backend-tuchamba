package com.acme.jobconnect.platform.users.domain.service;

import com.acme.jobconnect.platform.users.domain.model.aggregates.User;
import java.util.Optional;

public interface ProfileQueryService {
    Optional<User> handle(Long userId);
}
