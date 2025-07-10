package com.acme.jobconnect.platform.iam.interfaces.rest.transform;

import com.acme.jobconnect.platform.iam.domain.model.commands.SignUpCommand;
import com.acme.jobconnect.platform.iam.domain.model.entities.Role;
import com.acme.jobconnect.platform.iam.interfaces.rest.resources.SignUpRequest;

import java.util.List;
import java.util.stream.Collectors;

public class SignUpCommandFromResourceAssembler {
    public static SignUpCommand toCommandFromResource(SignUpRequest resource) {
        List<Role> roles = resource.roles().stream()
                .map(Role::toRoleFromName)
                .collect(Collectors.toList());
        return new SignUpCommand(resource.name(), resource.email(), resource.password(), roles);
    }
}
