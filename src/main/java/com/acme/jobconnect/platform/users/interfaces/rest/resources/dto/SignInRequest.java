package com.acme.jobconnect.platform.users.interfaces.rest.resources.dto;

// Aunque es estructuralmente igual a SignUpRequest, lo creamos por separado
// para mantener una semántica clara y permitir que evolucionen de forma independiente.
public record SignInRequest(String email, String password) {
}