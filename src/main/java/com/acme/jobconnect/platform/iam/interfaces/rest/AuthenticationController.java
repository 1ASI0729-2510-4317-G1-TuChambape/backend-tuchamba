package com.acme.jobconnect.platform.iam.interfaces.rest;

import com.acme.jobconnect.platform.iam.domain.services.AccountCommandService;
import com.acme.jobconnect.platform.iam.interfaces.rest.resources.AccountResource;
import com.acme.jobconnect.platform.iam.interfaces.rest.resources.SignInRequest;
import com.acme.jobconnect.platform.iam.interfaces.rest.resources.SignInResponse;
import com.acme.jobconnect.platform.iam.interfaces.rest.resources.SignUpRequest;
import com.acme.jobconnect.platform.iam.interfaces.rest.transform.AccountResourceFromEntityAssembler;
import com.acme.jobconnect.platform.iam.interfaces.rest.transform.SignInCommandFromResourceAssembler;
import com.acme.jobconnect.platform.iam.interfaces.rest.transform.SignUpCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/iam", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Authentication", description = "Authentication Endpoints")
public class AuthenticationController {

    private final AccountCommandService accountCommandService;

    public AuthenticationController(AccountCommandService accountCommandService) {
        this.accountCommandService = accountCommandService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<AccountResource> signUp(@RequestBody SignUpRequest resource) {
        var signUpCommand = SignUpCommandFromResourceAssembler.toCommandFromResource(resource);
        var account = accountCommandService.handle(signUpCommand);
        if (account.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var accountResource = AccountResourceFromEntityAssembler.toResourceFromEntity(account.get());
        return new ResponseEntity<>(accountResource, HttpStatus.CREATED);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<SignInResponse> signIn(@RequestBody SignInRequest resource) {
        var signInCommand = SignInCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = accountCommandService.handle(signInCommand);
        if (result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        var token = result.get().getRight();
        return ResponseEntity.ok(new SignInResponse(token));
    }
}
