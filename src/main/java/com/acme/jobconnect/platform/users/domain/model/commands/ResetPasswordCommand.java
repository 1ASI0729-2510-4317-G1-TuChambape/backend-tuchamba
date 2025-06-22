package com.acme.jobconnect.platform.users.domain.model.commands;
public record ResetPasswordCommand(String resetToken, String newPassword) {}