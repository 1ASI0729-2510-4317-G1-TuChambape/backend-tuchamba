package com.acme.jobconnect.platform.profiles.domain.service;

import com.acme.jobconnect.platform.profiles.domain.model.aggregates.Profile;
import java.util.Optional;

public interface ProfileQueryService {
    Optional<Profile> findByUserEmail(String email);
}