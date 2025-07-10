package com.acme.jobconnect.platform.users.application.internal.queryservices;

import com.acme.jobconnect.platform.users.domain.model.aggregates.Customer;
import com.acme.jobconnect.platform.users.domain.model.queries.GetAllCustomersQuery;
import com.acme.jobconnect.platform.users.domain.model.queries.GetCustomerByIdQuery;
import com.acme.jobconnect.platform.users.domain.service.CustomerQueryService;
import com.acme.jobconnect.platform.users.infrastructure.persistence.jpa.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerQueryServiceImpl implements CustomerQueryService {

    private final CustomerRepository customerRepository;

    @Override
    public Optional<Customer> handle(GetCustomerByIdQuery query) {
        return customerRepository.findById(query.id());
    }

    @Override
    public List<Customer> handle(GetAllCustomersQuery query) {
        return customerRepository.findAll();
    }
} 