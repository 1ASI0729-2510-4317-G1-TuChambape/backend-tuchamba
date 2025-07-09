package com.acme.jobconnect.platform.profiles.interfaces.rest.resources.dto;

// import java.util.Date; // Comentado por ahora

public record ProfileResource(
        Long id,
        String email,
        String firstName,
        String lastName
        // El resto de campos comentados para que coincida con el ensamblador
        // String documentType,
        // String documentNumber,
        // Date birthDate,
        // String gender,
        // String phone,
        // String country,
        // String city,
        // String address
) {
}