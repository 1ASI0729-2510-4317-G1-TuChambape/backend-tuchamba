package com.acme.jobconnect.platform.offers.domain.services;

import com.acme.jobconnect.platform.offers.domain.model.entities.Review;
import com.acme.jobconnect.platform.offers.domain.model.queries.GetAllReviewsQuery;
import com.acme.jobconnect.platform.offers.domain.model.queries.GetReviewByIdQuery;

import java.util.List;
import java.util.Optional;

public interface ReviewQueryService {
    Optional<Review> handle(GetReviewByIdQuery query);
    List<Review> handle(GetAllReviewsQuery query);
} 