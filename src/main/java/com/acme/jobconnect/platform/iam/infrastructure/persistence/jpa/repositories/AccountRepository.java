package com.acme.jobconnect.platform.iam.infrastructure.persistence.jpa.repositories;

import com.acme.jobconnect.platform.iam.domain.model.aggregates.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByEmail(String email);
    boolean existsByEmail(String email);
} 