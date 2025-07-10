package com.acme.jobconnect.platform.users.domain.service;

import com.acme.jobconnect.platform.users.domain.model.commands.CreateCustomerProfileCommand;
import com.acme.jobconnect.platform.users.domain.model.commands.CreateUserCommand;
import com.acme.jobconnect.platform.users.domain.model.commands.CreateWorkerProfileCommand;
import com.acme.jobconnect.platform.users.domain.model.commands.DeleteCustomerCommand;
import com.acme.jobconnect.platform.users.domain.model.commands.DeleteUserCommand;
import com.acme.jobconnect.platform.users.domain.model.commands.DeleteWorkerCommand;
import com.acme.jobconnect.platform.users.domain.model.commands.UpdateCustomerCommand;
import com.acme.jobconnect.platform.users.domain.model.commands.UpdateWorkerCommand;

import java.util.Optional;

public interface UserCommandService {
    Optional<Long> handle(CreateWorkerProfileCommand command);
    Optional<Long> handle(CreateCustomerProfileCommand command);
    Optional<Long> handle(CreateUserCommand command);
    Optional<Long> handle(UpdateWorkerCommand command);
    void handle(DeleteWorkerCommand command);
    void handle(DeleteUserCommand command);
    Optional<Long> handle(UpdateCustomerCommand command);
    void handle(DeleteCustomerCommand command);
} 