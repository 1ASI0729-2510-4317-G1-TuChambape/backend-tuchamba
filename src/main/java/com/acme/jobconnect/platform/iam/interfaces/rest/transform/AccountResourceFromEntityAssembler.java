package com.acme.jobconnect.platform.iam.interfaces.rest.transform;

import com.acme.jobconnect.platform.iam.domain.model.aggregates.Account;
import com.acme.jobconnect.platform.iam.interfaces.rest.resources.AccountResource;

import java.util.stream.Collectors;

public class AccountResourceFromEntityAssembler {
    public static AccountResource toResourceFromEntity(Account entity) {
        var roles = entity.getRoles().stream()
                .map(role -> role.getName().name())
                .collect(Collectors.toList());
        return new AccountResource(entity.getId(), entity.getName(), entity.getEmail(), roles);
    }
} 