package com.acme.jobconnect.platform.users.domain.service;

import com.acme.jobconnect.platform.users.domain.model.commands.CreateCustomerProfileCommand;
import com.acme.jobconnect.platform.users.domain.model.commands.CreateUserCommand;
import com.acme.jobconnect.platform.users.domain.model.commands.CreateWorkerProfileCommand;

import java.util.Optional;

public interface UserCommandService {
    Optional<Long> handle(CreateWorkerProfileCommand command);
    Optional<Long> handle(CreateCustomerProfileCommand command);
    Optional<Long> handle(CreateUserCommand command);
} 