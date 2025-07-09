package com.acme.jobconnect.platform.profiles.infrastructure.persistence.jpa.repositories;

import com.acme.jobconnect.platform.profiles.domain.model.aggregates.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    // La convención correcta para buscar por la propiedad 'email' del objeto anidado 'user' es "findByUserEmail"
    Optional<Profile> findByUserEmail(String email);
}