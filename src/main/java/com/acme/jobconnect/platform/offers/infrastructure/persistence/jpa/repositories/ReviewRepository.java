package com.acme.jobconnect.platform.offers.infrastructure.persistence.jpa.repositories;

import com.acme.jobconnect.platform.offers.domain.model.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByOfferId(Long offerId);
} 