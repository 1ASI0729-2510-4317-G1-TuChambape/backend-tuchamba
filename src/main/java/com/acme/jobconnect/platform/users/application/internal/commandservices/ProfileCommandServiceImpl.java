package com.acme.jobconnect.platform.users.application.internal.commandservices;

import com.acme.jobconnect.platform.users.domain.model.aggregates.User;
import com.acme.jobconnect.platform.users.domain.service.ProfileCommandService;
import com.acme.jobconnect.platform.users.infrastructure.persistence.jpa.repositories.UserRepository;
import com.acme.jobconnect.platform.users.interfaces.rest.resources.dto.UpdateProfileResource;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ProfileCommandServiceImpl implements ProfileCommandService {
    private final UserRepository userRepository;

    public ProfileCommandServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> handle(Long userId, UpdateProfileResource resource) {
        return userRepository.findById(userId).map(user -> {
            user.setFirstName(resource.firstName());
            user.setLastName(resource.lastName());
            user.setDocumentType(resource.documentType());
            user.setDocumentNumber(resource.documentNumber());
            user.setBirthDate(resource.birthDate());
            user.setGender(resource.gender());
            user.setPhone(resource.phone());
            user.setCountry(resource.country());
            user.setCity(resource.city());
            user.setAddress(resource.address());
            return userRepository.save(user);
        });
    }
}
