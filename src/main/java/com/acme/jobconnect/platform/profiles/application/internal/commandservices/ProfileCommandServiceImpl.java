package com.acme.jobconnect.platform.profiles.application.internal.commandservices;

import com.acme.jobconnect.platform.profiles.domain.model.aggregates.Profile;
import com.acme.jobconnect.platform.profiles.domain.service.ProfileCommandService;
import com.acme.jobconnect.platform.profiles.infrastructure.persistence.jpa.repositories.ProfileRepository;
import com.acme.jobconnect.platform.profiles.interfaces.rest.resources.dto.UpdateProfileResource; // Asegúrate que el import apunte a ...dto
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileCommandServiceImpl implements ProfileCommandService {
    private final ProfileRepository profileRepository;

    public ProfileCommandServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public Optional<Profile> updateProfile(String email, UpdateProfileResource resource) {
        return profileRepository.findByUserEmail(email).map(profile -> {
            profile.setFirstName(resource.firstName());
            profile.setLastName(resource.lastName());
            profile.setAddress(resource.address());
            profile.setCity(resource.city());
            profile.setCountry(resource.country());
            profile.setPhone(resource.phone());
            profile.setDocumentType(resource.documentType());
            profile.setDocumentNumber(resource.documentNumber());
            profile.setBirthDate(resource.birthDate());
            profile.setGender(resource.gender());
            return profileRepository.save(profile);
        });
    }
}