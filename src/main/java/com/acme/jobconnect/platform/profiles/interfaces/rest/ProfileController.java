// Paquete que define la capa de interfaz REST para perfiles
package com.acme.jobconnect.platform.profiles.interfaces.rest;

// Servicios del dominio encargados de la lógica de comandos y consultas para perfiles
import com.acme.jobconnect.platform.profiles.domain.service.ProfileCommandService;
import com.acme.jobconnect.platform.profiles.domain.service.ProfileQueryService;

// DTOs utilizados para enviar y recibir datos de perfil en las solicitudes HTTP
import com.acme.jobconnect.platform.profiles.interfaces.rest.resources.dto.ProfileResource;
import com.acme.jobconnect.platform.profiles.interfaces.rest.resources.dto.UpdateProfileResource;

// Clase que transforma entidades de perfil a recursos que serán devueltos al cliente
import com.acme.jobconnect.platform.profiles.interfaces.rest.transform.ProfileResourceFromEntityAssembler;

// Anotaciones de Swagger para documentación de la API
import io.swagger.v3.oas.annotations.tags.Tag;

// Clases de Spring para manejo de peticiones HTTP y autenticación
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

// Indica que esta clase es un controlador REST
@RestController

// Define la URL base para las rutas de este controlador y el tipo de respuesta por defecto (JSON)
@RequestMapping(value = "/api/v1/profiles", produces = MediaType.APPLICATION_JSON_VALUE)

// Etiqueta para documentación de Swagger
@Tag(name = "Profiles", description = "User Profile Management Endpoints")
public class ProfileController {

    // Servicios necesarios para manejar operaciones sobre perfiles
    private final ProfileQueryService profileQueryService;
    private final ProfileCommandService profileCommandService;

    // Constructor que inyecta los servicios a través del patrón de inyección de dependencias
    public ProfileController(ProfileQueryService profileQueryService, ProfileCommandService profileCommandService) {
        this.profileQueryService = profileQueryService;
        this.profileCommandService = profileCommandService;
    }

    // Endpoint GET para obtener el perfil del usuario autenticado
    @GetMapping("/me")
    public ResponseEntity<ProfileResource> getMyProfile() {
        // Se obtiene el nombre (email) del usuario autenticado
        var authenticatedUsername = SecurityContextHolder.getContext().getAuthentication().getName();

        // Se busca el perfil asociado al email del usuario
        var profile = profileQueryService.findByUserEmail(authenticatedUsername)
                .orElseThrow(() -> new RuntimeException("Profile not found for current user")); // Error si no se encuentra

        // Se transforma la entidad del perfil en un recurso que será devuelto al cliente
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile);

        // Se retorna el recurso del perfil con estado 200 OK
        return ResponseEntity.ok(profileResource);
    }

    // Endpoint PUT para actualizar el perfil del usuario autenticado
    @PutMapping("/me")
    public ResponseEntity<ProfileResource> updateMyProfile(@RequestBody UpdateProfileResource resource) {
        // Se obtiene el nombre (email) del usuario autenticado
        var authenticatedUsername = SecurityContextHolder.getContext().getAuthentication().getName();

        // Se actualiza el perfil usando los datos proporcionados
        var updatedProfile = profileCommandService.updateProfile(authenticatedUsername, resource)
                .orElseThrow(() -> new RuntimeException("Error while updating profile")); // Error si falla la actualización

        // Se transforma la entidad actualizada en un recurso
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(updatedProfile);

        // Se retorna el recurso actualizado con estado 200 OK
        return ResponseEntity.ok(profileResource);
    }
}
