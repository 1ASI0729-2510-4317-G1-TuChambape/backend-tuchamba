package com.acme.jobconnect.platform.users.application.internal.commandservices;

import com.acme.jobconnect.platform.users.domain.model.aggregates.User;
import com.acme.jobconnect.platform.users.domain.model.commands.ForgotPasswordCommand;
import com.acme.jobconnect.platform.users.domain.model.commands.ResetPasswordCommand;
import com.acme.jobconnect.platform.users.domain.model.commands.SignUpCommand;
import com.acme.jobconnect.platform.users.domain.model.commands.VerifyResetTokenCommand;
import com.acme.jobconnect.platform.users.domain.service.UserCommandService;
import com.acme.jobconnect.platform.users.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserCommandServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Long handle(SignUpCommand command) {
        if (userRepository.existsByEmail(command.email())) {
            throw new IllegalArgumentException("User with email " + command.email() + " already exists");
        }
        String encodedPassword = passwordEncoder.encode(command.password());
        var user = new User(command.email(), encodedPassword);
        userRepository.save(user);
        return user.getId();
    }

    @Override
    public void handle(ForgotPasswordCommand command) {
        userRepository.findByEmail(command.email()).ifPresent(user -> {
            user.generateResetToken();
            userRepository.save(user);

            // Para nuestra prueba, lo imprimimos en la consola.
            System.out.println("Generated Reset Token for user " + user.getEmail() + ": " + user.getResetToken());
        });
        // NOTA: No devolvemos error si el email no existe. Es una práctica de seguridad
        // para no permitir que alguien descubra qué emails están registrados en el sistema.
    }

    @Override
    public boolean handle(VerifyResetTokenCommand command) {
        var user = userRepository.findByResetToken(command.resetToken())
                .orElse(null);

        // Comprueba si el usuario existe y si el token no ha expirado
        if (user == null || user.getResetTokenExpiry().before(new Date())) {
            return false; // Token inválido o expirado
        }

        return true; // Token válido
    }

    @Override
    public void handle(ResetPasswordCommand command) {
        var user = userRepository.findByResetToken(command.resetToken())
                .orElseThrow(() -> new IllegalArgumentException("Invalid reset token."));

        // Re-verificar que el token no haya expirado
        if (user.getResetTokenExpiry().before(new Date())) {
            user.clearResetToken(); // Limpiar el token expirado por seguridad
            userRepository.save(user);
            throw new IllegalArgumentException("Reset token has expired.");
        }

        // Encriptar la nueva contraseña
        String encodedPassword = passwordEncoder.encode(command.newPassword());
        user.setPassword(encodedPassword);

        // Limpiar el token de reseteo para que no se pueda volver a usar
        user.clearResetToken();

        // Guardar los cambios en el usuario
        userRepository.save(user);
    }
}