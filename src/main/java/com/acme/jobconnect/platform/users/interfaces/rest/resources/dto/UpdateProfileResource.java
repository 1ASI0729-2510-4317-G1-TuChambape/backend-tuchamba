package com.acme.jobconnect.platform.users.interfaces.rest.resources.dto;

import java.util.Date;

public record UpdateProfileResource(
        String firstName,
        String lastName,
        String documentType,
        String documentNumber,
        Date birthDate,
        String gender,
        String phone,
        String country,
        String city,
        String address
) {
}
