package com.acme.jobconnect.platform.users.domain.service;

import com.acme.jobconnect.platform.users.domain.model.commands.ForgotPasswordCommand;
import com.acme.jobconnect.platform.users.domain.model.commands.ResetPasswordCommand;
import com.acme.jobconnect.platform.users.domain.model.commands.SignUpCommand;
import com.acme.jobconnect.platform.users.domain.model.commands.VerifyResetTokenCommand;

public interface UserCommandService {

    /**
     * Maneja el comando para registrar un nuevo usuario.
     * @param command El comando que contiene el email y la contraseña del nuevo usuario.
     * @return El ID del usuario recién creado.
     */
    Long handle(SignUpCommand command);

    /**
     * Maneja el comando para solicitar la recuperación de contraseña.
     * @param command El comando que contiene el email del usuario.
     */
    void handle(ForgotPasswordCommand command);

    /**
     * Maneja el comando para verificar un token de reseteo de contraseña.
     * @param command El comando que contiene el token de reseteo.
     * @return true si el token es válido y no ha expirado, false en caso contrario.
     */
    boolean handle(VerifyResetTokenCommand command);

    /**
     * Maneja el comando para establecer una nueva contraseña usando un token de reseteo.
     * @param command El comando que contiene el token de reseteo y la nueva contraseña.
     */
    void handle(ResetPasswordCommand command);
}