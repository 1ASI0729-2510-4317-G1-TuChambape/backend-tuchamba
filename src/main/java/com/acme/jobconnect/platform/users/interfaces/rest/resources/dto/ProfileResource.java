package com.acme.jobconnect.platform.users.interfaces.rest.resources.dto;

import java.util.Date;

public record ProfileResource(
        Long id,
        String email,
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
