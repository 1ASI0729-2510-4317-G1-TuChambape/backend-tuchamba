package com.acme.jobconnect.platform.users.application.internal.commandservices;

import com.acme.jobconnect.platform.profiles.domain.model.aggregates.Profile;
import com.acme.jobconnect.platform.profiles.infrastructure.persistence.jpa.repositories.ProfileRepository;
import com.acme.jobconnect.platform.users.domain.model.aggregates.User;
import com.acme.jobconnect.platform.users.domain.model.commands.*;
import com.acme.jobconnect.platform.users.domain.service.UserCommandService;
import com.acme.jobconnect.platform.users.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;

    public UserCommandServiceImpl(UserRepository userRepository, ProfileRepository profileRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Long handle(SignUpCommand command) {
        if (userRepository.existsByEmail(command.email())) {
            throw new IllegalArgumentException("User with email " + command.email() + " already exists");
        }
        var user = new User(command.email(), passwordEncoder.encode(command.password()), command.roles());
        userRepository.save(user);
        var profile = new Profile(user, command.firstName(), command.lastName());
        profileRepository.save(profile);
        return user.getId();
    }

    @Override
    public void handle(ForgotPasswordCommand command) {
        userRepository.findByEmail(command.email()).ifPresent(user -> {
            user.generateResetToken();
            userRepository.save(user);
        });
    }

    @Override
    public boolean handle(VerifyResetTokenCommand command) {
        var user = userRepository.findByResetToken(command.resetToken()).orElse(null);
        if (user == null || user.getResetTokenExpiry().before(new Date())) {
            return false;
        }
        return true;
    }

    @Override
    public void handle(ResetPasswordCommand command) {
        var user = userRepository.findByResetToken(command.resetToken())
                .orElseThrow(() -> new IllegalArgumentException("Invalid reset token."));
        if (user.getResetTokenExpiry().before(new Date())) {
            user.clearResetToken();
            userRepository.save(user);
            throw new IllegalArgumentException("Reset token has expired.");
        }
        user.setPassword(passwordEncoder.encode(command.newPassword()));
        user.clearResetToken();
        userRepository.save(user);
    }
}