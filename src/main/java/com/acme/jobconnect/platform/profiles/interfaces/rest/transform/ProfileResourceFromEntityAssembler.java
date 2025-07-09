package com.acme.jobconnect.platform.profiles.interfaces.rest.transform;

import com.acme.jobconnect.platform.profiles.domain.model.aggregates.Profile;
import com.acme.jobconnect.platform.profiles.interfaces.rest.resources.dto.ProfileResource;

public class ProfileResourceFromEntityAssembler {
    public static ProfileResource toResourceFromEntity(Profile entity) {
        var email = entity.getUser() != null ? entity.getUser().getEmail() : "";
        return new ProfileResource(
                entity.getId(),
                email,
                entity.getFirstName(),
                entity.getLastName()
                // El resto de los campos se han quitado para que coincida
                // con la versión simplificada de ProfileResource que hicimos para que compilara.
        );
    }
}