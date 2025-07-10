package com.acme.jobconnect.platform.offers.infrastructure.persistence.jpa.repositories;

import com.acme.jobconnect.platform.offers.domain.model.aggregates.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
} 