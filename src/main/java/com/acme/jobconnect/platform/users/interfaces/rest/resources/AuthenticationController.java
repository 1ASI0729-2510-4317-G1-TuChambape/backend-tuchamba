package com.acme.jobconnect.platform.users.interfaces.rest.resources;

import com.acme.jobconnect.platform.security.jwt.TokenServiceImpl;
import com.acme.jobconnect.platform.users.domain.model.commands.ForgotPasswordCommand;
import com.acme.jobconnect.platform.users.domain.model.commands.ResetPasswordCommand;
import com.acme.jobconnect.platform.users.domain.model.commands.SignUpCommand;
import com.acme.jobconnect.platform.users.domain.model.commands.VerifyResetTokenCommand;
import com.acme.jobconnect.platform.users.domain.service.UserCommandService;
import com.acme.jobconnect.platform.users.infrastructure.persistence.jpa.repositories.UserRepository;
import com.acme.jobconnect.platform.users.interfaces.rest.resources.dto.*;
import com.acme.jobconnect.platform.users.interfaces.rest.transform.UserResourceFromEntityAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/authentication", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

    private final UserCommandService userCommandService;
    private final TokenServiceImpl tokenService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    public AuthenticationController(UserCommandService userCommandService, TokenServiceImpl tokenService, AuthenticationManager authenticationManager, UserRepository userRepository) {
        this.userCommandService = userCommandService;
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    /**
     * Handles the sign-up request.
     */
    @PostMapping("/sign-up")
    public ResponseEntity<UserResource> signUp(@RequestBody SignUpRequest signUpRequest) {
        var signUpCommand = new SignUpCommand(signUpRequest.email(), signUpRequest.password());
        var userId = userCommandService.handle(signUpCommand);
        if (userId == null) {
            return ResponseEntity.badRequest().build();
        }
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found after creation"));
        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user);
        return new ResponseEntity<>(userResource, HttpStatus.CREATED);
    }

    /**
     * Handles the sign-in request.
     */
    @PostMapping("/sign-in")
    public ResponseEntity<SignInResponse> signIn(@RequestBody SignInRequest signInRequest) {
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.email(), signInRequest.password())
        );
        var token = tokenService.generateToken(authentication);
        return ResponseEntity.ok(new SignInResponse(token));
    }

    /**
     * Handles the forgot-password request.
     */
    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordRequest forgotPasswordRequest) {
        var forgotPasswordCommand = new ForgotPasswordCommand(forgotPasswordRequest.email());
        userCommandService.handle(forgotPasswordCommand);
        return ResponseEntity.ok("If a user with that email exists, a reset token has been generated.");
    }

    /**
     * Handles the verify-token request.
     */
    @PostMapping("/verify-code")
    public ResponseEntity<VerifyTokenResponse> verifyToken(@RequestBody VerifyTokenRequest verifyTokenRequest) {
        var verifyTokenCommand = new VerifyResetTokenCommand(verifyTokenRequest.resetToken());
        boolean isTokenValid = userCommandService.handle(verifyTokenCommand);
        return ResponseEntity.ok(new VerifyTokenResponse(isTokenValid));
    }

    /**
     * Handles the reset-password request.
     */
    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest) {
        var resetPasswordCommand = new ResetPasswordCommand(
                resetPasswordRequest.resetToken(),
                resetPasswordRequest.newPassword()
        );
        userCommandService.handle(resetPasswordCommand);
        return ResponseEntity.ok("Password has been successfully reset.");
    }
}