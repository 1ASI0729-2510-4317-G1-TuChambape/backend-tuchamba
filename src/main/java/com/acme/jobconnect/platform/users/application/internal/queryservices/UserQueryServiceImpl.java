package com.acme.jobconnect.platform.users.application.internal.queryservices;

import com.acme.jobconnect.platform.users.domain.model.aggregates.User;
import com.acme.jobconnect.platform.users.domain.model.aggregates.Customer;
import com.acme.jobconnect.platform.users.domain.model.aggregates.Worker;
import com.acme.jobconnect.platform.users.domain.model.queries.*;
import com.acme.jobconnect.platform.users.domain.service.UserQueryService;
import com.acme.jobconnect.platform.users.infrastructure.persistence.jpa.repositories.CustomerRepository;
import com.acme.jobconnect.platform.users.infrastructure.persistence.jpa.repositories.UserRepository;
import com.acme.jobconnect.platform.users.infrastructure.persistence.jpa.repositories.WorkerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository userRepository;
    private final WorkerRepository workerRepository;
    private final CustomerRepository customerRepository;


    @Override
    public List<User> handle(GetAllUsersQuery query) {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> handle(GetUserByIdQuery query) {
        return userRepository.findById(query.id());
    }

    @Override
    public Optional<User> handle(GetUserByAccountIdQuery query) {
        return userRepository.findByAccountId(query.accountId());
    }

    @Override
    public Optional<Worker> handle(GetWorkerByIdQuery query) {
        return workerRepository.findById(query.id());
    }

    @Override
    public Optional<Customer> handle(GetCustomerByIdQuery query) {
        return customerRepository.findById(query.id());
    }
} 