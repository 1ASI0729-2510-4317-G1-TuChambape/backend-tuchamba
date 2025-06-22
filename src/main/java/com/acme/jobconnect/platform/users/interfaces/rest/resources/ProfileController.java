package com.acme.jobconnect.platform.users.interfaces.rest.resources;

import com.acme.jobconnect.platform.users.domain.model.aggregates.User;
import com.acme.jobconnect.platform.users.domain.service.ProfileCommandService;
import com.acme.jobconnect.platform.users.domain.service.ProfileQueryService;
import com.acme.jobconnect.platform.users.infrastructure.persistence.jpa.repositories.UserRepository;
import com.acme.jobconnect.platform.users.interfaces.rest.resources.dto.ProfileResource;
import com.acme.jobconnect.platform.users.interfaces.rest.resources.dto.UpdateProfileResource;
import com.acme.jobconnect.platform.users.interfaces.rest.transform.ProfileResourceFromEntityAssembler;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/profiles", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileController {

    private final ProfileCommandService profileCommandService;
    private final UserRepository userRepository; // Necesario para buscar al usuario autenticado

    public ProfileController(ProfileCommandService profileCommandService, UserRepository userRepository) {
        this.profileCommandService = profileCommandService;
        this.userRepository = userRepository;
    }

    /**
     * Obtiene el perfil del usuario actualmente autenticado.
     * @return El perfil del usuario como un ProfileResource.
     */
    @GetMapping("/me")
    public ResponseEntity<ProfileResource> getMyProfile() {
        // Obtiene el email del usuario autenticado desde el contexto de seguridad de Spring
        var authenticatedUsername = SecurityContextHolder.getContext().getAuthentication().getName();

        // Busca al usuario en la base de datos por su email
        return userRepository.findByEmail(authenticatedUsername)
                .map(user -> {
                    // Si se encuentra, lo convierte a un DTO y lo devuelve
                    var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(user);
                    return ResponseEntity.ok(profileResource);
                })
                // Si no se encuentra por alguna razón, devuelve un error 404 Not Found
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Actualiza el perfil del usuario actualmente autenticado.
     * @param resource El cuerpo de la petición con los datos a actualizar.
     * @return El perfil del usuario ya actualizado.
     */
    @PutMapping("/me")
    public ResponseEntity<ProfileResource> updateMyProfile(@RequestBody UpdateProfileResource resource) {
        // Obtiene el email del usuario autenticado
        var authenticatedUsername = SecurityContextHolder.getContext().getAuthentication().getName();

        // Busca al usuario para obtener su ID
        return userRepository.findByEmail(authenticatedUsername)
                .map(user -> {
                    // Usa el ID para llamar al servicio de comando de actualización
                    var updatedUser = profileCommandService.handle(user.getId(), resource)
                            .orElseThrow(() -> new RuntimeException("Error while updating profile")); // Manejo de error simple

                    var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(updatedUser);
                    return ResponseEntity.ok(profileResource);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}