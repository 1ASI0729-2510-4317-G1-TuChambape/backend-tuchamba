package com.acme.jobconnect.platform.profiles.domain.service;

import com.acme.jobconnect.platform.profiles.domain.model.aggregates.Profile;
import com.acme.jobconnect.platform.profiles.interfaces.rest.resources.dto.UpdateProfileResource; // Asegúrate que el import apunte a ...dto
import java.util.Optional;

public interface ProfileCommandService {
    Optional<Profile> updateProfile(String email, UpdateProfileResource resource);
}