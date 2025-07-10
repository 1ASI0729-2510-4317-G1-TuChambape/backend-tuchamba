package com.acme.jobconnect.platform.iam.interfaces.rest.resources;

public record AuthenticatedUserResource(Long id, String username, String token) {

}
