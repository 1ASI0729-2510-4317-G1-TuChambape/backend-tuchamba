package com.acme.jobconnect.platform.users.interfaces.rest.transform;

import com.acme.jobconnect.platform.users.domain.model.aggregates.User;
import com.acme.jobconnect.platform.users.interfaces.rest.resources.dto.ProfileResource;

public class ProfileResourceFromEntityAssembler {
    public static ProfileResource toResourceFromEntity(User entity) {
        return new ProfileResource(
                entity.getId(),
                entity.getEmail(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getDocumentType(),
                entity.getDocumentNumber(),
                entity.getBirthDate(),
                entity.getGender(),
                entity.getPhone(),
                entity.getCountry(),
                entity.getCity(),
                entity.getAddress()
        );
    }
}
