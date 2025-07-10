package com.acme.jobconnect.platform.users.infrastructure.persistence.jpa.repositories;

import com.acme.jobconnect.platform.users.domain.model.aggregates.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Long> {
    boolean existsByEmail(String email);
} 