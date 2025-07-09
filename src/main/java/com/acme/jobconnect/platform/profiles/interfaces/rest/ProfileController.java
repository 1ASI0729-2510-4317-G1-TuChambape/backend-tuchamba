package com.acme.jobconnect.platform.profiles.interfaces.rest;

import com.acme.jobconnect.platform.profiles.domain.service.ProfileCommandService;
import com.acme.jobconnect.platform.profiles.domain.service.ProfileQueryService;
import com.acme.jobconnect.platform.profiles.interfaces.rest.resources.dto.ProfileResource; // Asegúrate que el import apunte a ...dto
import com.acme.jobconnect.platform.profiles.interfaces.rest.resources.dto.UpdateProfileResource; // Asegúrate que el import apunte a ...dto
import com.acme.jobconnect.platform.profiles.interfaces.rest.transform.ProfileResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/profiles", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Profiles", description = "User Profile Management Endpoints")
public class ProfileController {

    private final ProfileQueryService profileQueryService;
    private final ProfileCommandService profileCommandService;

    public ProfileController(ProfileQueryService profileQueryService, ProfileCommandService profileCommandService) {
        this.profileQueryService = profileQueryService;
        this.profileCommandService = profileCommandService;
    }

    @GetMapping("/me")
    public ResponseEntity<ProfileResource> getMyProfile() {
        var authenticatedUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        var profile = profileQueryService.findByUserEmail(authenticatedUsername)
                .orElseThrow(() -> new RuntimeException("Profile not found for current user"));
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile);
        return ResponseEntity.ok(profileResource);
    }

    @PutMapping("/me")
    public ResponseEntity<ProfileResource> updateMyProfile(@RequestBody UpdateProfileResource resource) {
        var authenticatedUsername = SecurityContextHolder.getContext().getAuthentication().getName();

        var updatedProfile = profileCommandService.updateProfile(authenticatedUsername, resource)
                .orElseThrow(() -> new RuntimeException("Error while updating profile"));

        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(updatedProfile);
        return ResponseEntity.ok(profileResource);
    }
}