package com.acme.jobconnect.platform.profiles.application.internal.queryservices;

import com.acme.jobconnect.platform.profiles.domain.model.aggregates.Profile;
import com.acme.jobconnect.platform.profiles.domain.service.ProfileQueryService;
import com.acme.jobconnect.platform.profiles.infrastructure.persistence.jpa.repositories.ProfileRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ProfileQueryServiceImpl implements ProfileQueryService {
    private final ProfileRepository profileRepository;

    public ProfileQueryServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public Optional<Profile> findByUserEmail(String email) {
        return profileRepository.findByUserEmail(email);
    }
}