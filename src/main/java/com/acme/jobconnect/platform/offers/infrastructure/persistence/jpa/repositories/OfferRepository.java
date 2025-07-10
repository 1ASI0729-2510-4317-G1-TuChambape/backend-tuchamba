package com.acme.jobconnect.platform.offers.infrastructure.persistence.jpa.repositories;

import com.acme.jobconnect.platform.offers.domain.model.aggregates.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
    Optional<Offer> findByTitle(String title);
    List<Offer> findAllByStatus(String status);
    List<Offer> findAllByClientId(Long clientId);
    List<Offer> findAllByStatusAndClientId(String status, Long clientId);
} 