package com.acme.jobconnect.platform.users.application.internal.commandservices;

import com.acme.jobconnect.platform.users.domain.model.aggregates.User;
import com.acme.jobconnect.platform.users.domain.model.commands.CreateCustomerProfileCommand;
import com.acme.jobconnect.platform.users.domain.model.commands.CreateWorkerProfileCommand;
import com.acme.jobconnect.platform.users.domain.model.commands.CreateUserCommand;
import com.acme.jobconnect.platform.users.domain.model.aggregates.Customer;
import com.acme.jobconnect.platform.users.domain.model.aggregates.Worker;
import com.acme.jobconnect.platform.users.domain.service.UserCommandService;
import com.acme.jobconnect.platform.users.infrastructure.persistence.jpa.repositories.CustomerRepository;
import com.acme.jobconnect.platform.users.infrastructure.persistence.jpa.repositories.UserRepository;
import com.acme.jobconnect.platform.users.infrastructure.persistence.jpa.repositories.WorkerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final WorkerRepository workerRepository;
    private final CustomerRepository customerRepository;

    @Override
    public Optional<Long> handle(CreateWorkerProfileCommand command) {
        if (userRepository.findByAccountId(command.accountId()).isPresent() || workerRepository.existsByEmail(command.email())) {
            throw new IllegalArgumentException("User with this accountId or email already exists");
        }

        var worker = new Worker(
                command.email(),
                command.firstName(),
                command.lastName(),
                command.phone(),
                command.avatar(),
                command.location(),
                command.bio(),
                command.skills(),
                command.experience(),
                command.certifications(),
                command.monday(),
                command.tuesday(),
                command.wednesday(),
                command.thursday(),
                command.friday(),
                command.saturday(),
                command.sunday(),
                command.yapeNumber(),
                command.plinNumber(),
                command.bankAccountNumber()
        );
        workerRepository.save(worker);

        var user = new User(command.accountId(), worker, null);
        userRepository.save(user);

        return Optional.of(worker.getId());
    }

    @Override
    public Optional<Long> handle(CreateCustomerProfileCommand command) {
        if (userRepository.findByAccountId(command.accountId()).isPresent() || customerRepository.existsByEmail(command.email())) {
            throw new IllegalArgumentException("User with this accountId or email already exists");
        }
        var customer = new Customer(
                command.email(),
                command.firstName(),
                command.lastName(),
                command.phone(),
                command.location(),
                command.bio()
        );
        customerRepository.save(customer);

        var user = new User(command.accountId(), null, customer);
        userRepository.save(user);

        return Optional.of(customer.getId());
    }

    @Override
    public Optional<Long> handle(CreateUserCommand command) {
        if (userRepository.findByAccountId(command.accountId()).isPresent()) {
            throw new IllegalArgumentException("User with this accountId already exists");
        }

        var worker = workerRepository.findById(command.workerId())
                .orElseThrow(() -> new IllegalArgumentException("Worker with this id does not exist"));

        Customer customer = null;
        if (command.customerId() != null) {
            customer = customerRepository.findById(command.customerId())
                    .orElseThrow(() -> new IllegalArgumentException("Customer with this id does not exist"));
        }

        var user = new User(command.accountId(), worker, customer);
        userRepository.save(user);

        return Optional.of(user.getId());
    }
} 