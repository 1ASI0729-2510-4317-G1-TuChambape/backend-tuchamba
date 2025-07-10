package com.acme.jobconnect.platform.users.infrastructure.persistence.jpa.repositories;

import com.acme.jobconnect.platform.users.domain.model.aggregates.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    boolean existsByEmail(String email);
} 