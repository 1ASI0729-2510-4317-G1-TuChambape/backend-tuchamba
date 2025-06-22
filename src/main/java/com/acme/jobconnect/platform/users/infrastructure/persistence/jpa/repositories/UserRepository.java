package com.acme.jobconnect.platform.users.infrastructure.persistence.jpa.repositories;

import com.acme.jobconnect.platform.users.domain.model.aggregates.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    // ========= NUEVO MÉTODO AÑADIDO =========
    Optional<User> findByResetToken(String resetToken);
}