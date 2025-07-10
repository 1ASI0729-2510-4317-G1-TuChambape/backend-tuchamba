package com.acme.jobconnect.platform.users.domain.service;

import com.acme.jobconnect.platform.users.domain.model.aggregates.Customer;
import com.acme.jobconnect.platform.users.domain.model.queries.GetAllCustomersQuery;
import com.acme.jobconnect.platform.users.domain.model.queries.GetCustomerByIdQuery;

import java.util.List;
import java.util.Optional;

public interface CustomerQueryService {
    Optional<Customer> handle(GetCustomerByIdQuery query);
    List<Customer> handle(GetAllCustomersQuery query);
} 