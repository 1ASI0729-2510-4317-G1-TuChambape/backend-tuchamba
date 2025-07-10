package com.acme.jobconnect.platform.users.domain.service;

import com.acme.jobconnect.platform.users.domain.model.aggregates.User;
import com.acme.jobconnect.platform.users.domain.model.aggregates.Customer;
import com.acme.jobconnect.platform.users.domain.model.aggregates.Worker;
import com.acme.jobconnect.platform.users.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface UserQueryService {
    List<User> handle(GetAllUsersQuery query);
    Optional<User> handle(GetUserByIdQuery query);
    Optional<User> handle(GetUserByAccountIdQuery query);
    Optional<Worker> handle(GetWorkerByIdQuery query);
    Optional<Customer> handle(GetCustomerByIdQuery query);
} 