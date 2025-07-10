package com.acme.jobconnect.platform.users.interfaces.rest.transform;

import com.acme.jobconnect.platform.users.domain.model.entities.Preferences;
import com.acme.jobconnect.platform.users.interfaces.rest.resources.PreferencesResource;

public class PreferencesResourceFromEntityAssembler {
    public static PreferencesResource toResourceFromEntity(Preferences entity) {
        if (entity == null) {
            return null;
        }
        return new PreferencesResource(
                entity.getSkills(),
                entity.getTools(),
                entity.getJobModality(),
                entity.getJobExperience()
        );
    }
} 