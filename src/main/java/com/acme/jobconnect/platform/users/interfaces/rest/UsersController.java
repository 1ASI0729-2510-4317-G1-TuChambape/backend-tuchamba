package com.acme.jobconnect.platform.users.interfaces.rest;

import com.acme.jobconnect.platform.users.domain.model.commands.CreateUserCommand;
import com.acme.jobconnect.platform.users.domain.model.commands.DeleteUserCommand;
import com.acme.jobconnect.platform.users.domain.model.commands.UpdateUserCommand;
import com.acme.jobconnect.platform.users.domain.model.queries.GetAllUsersQuery;
import com.acme.jobconnect.platform.users.domain.model.queries.GetUserByAccountIdQuery;
import com.acme.jobconnect.platform.users.domain.model.queries.GetUserByIdQuery;
import com.acme.jobconnect.platform.users.domain.service.UserCommandService;
import com.acme.jobconnect.platform.users.domain.service.UserQueryService;
import com.acme.jobconnect.platform.users.interfaces.rest.resources.CreateUserResource;
import com.acme.jobconnect.platform.users.interfaces.rest.resources.UpdateUserResource;
import com.acme.jobconnect.platform.users.interfaces.rest.resources.UserResource;
import com.acme.jobconnect.platform.users.interfaces.rest.transform.CreateUserCommandFromResourceAssembler;
import com.acme.jobconnect.platform.users.interfaces.rest.transform.UpdateUserCommandFromResourceAssembler;
import com.acme.jobconnect.platform.users.interfaces.rest.transform.UserResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Users", description = "User Management Endpoints")
@RequiredArgsConstructor
public class UsersController {
    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;

    @PostMapping
    public ResponseEntity<UserResource> createUser(@RequestBody CreateUserResource resource) {
        var command = CreateUserCommandFromResourceAssembler.toCommandFromResource(resource);
        var userId = userCommandService.handle(command).orElseThrow();
        var user = userQueryService.handle(new GetUserByIdQuery(userId)).orElseThrow();
        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user);
        return new ResponseEntity<>(userResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity getAllUsers() {
        var getAllUsersQuery = new GetAllUsersQuery();
        var users = userQueryService.handle(getAllUsersQuery);
        var userResources = users.stream()
                .map(UserResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(userResources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResource> getUserById(@PathVariable Long id) {
        var getUserByIdQuery = new GetUserByIdQuery(id);
        var user = userQueryService.handle(getUserByIdQuery);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return ResponseEntity.ok(userResource);
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<UserResource> getUserByAccountId(@PathVariable Long accountId) {
        var getUserByAccountIdQuery = new GetUserByAccountIdQuery(accountId);
        var user = userQueryService.handle(getUserByAccountIdQuery);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return ResponseEntity.ok(userResource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResource> updateUser(@PathVariable Long id, @RequestBody UpdateUserResource resource) {
        var command = UpdateUserCommandFromResourceAssembler.toCommandFromResource(id, resource);
        var userId = userCommandService.handle(command).orElseThrow();
        var user = userQueryService.handle(new GetUserByIdQuery(userId)).orElseThrow();
        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user);
        return ResponseEntity.ok(userResource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        var command = new DeleteUserCommand(id);
        userCommandService.handle(command);
        return ResponseEntity.noContent().build();
    }
} 