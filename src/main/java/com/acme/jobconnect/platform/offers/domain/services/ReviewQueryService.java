package com.acme.jobconnect.platform.offers.domain.services;

import com.acme.jobconnect.platform.offers.domain.model.entities.Review;
import com.acme.jobconnect.platform.offers.domain.model.queries.GetAllReviewsByAuthorUserIdQuery;
import com.acme.jobconnect.platform.offers.domain.model.queries.GetAllReviewsByOfferIdQuery;
import com.acme.jobconnect.platform.offers.domain.model.queries.GetAllReviewsByReviewerUserIdQuery;
import com.acme.jobconnect.platform.offers.domain.model.queries.GetReviewByIdQuery;

import java.util.List;
import java.util.Optional;

public interface ReviewQueryService {
    Optional<Review> handle(GetReviewByIdQuery query);
    List<Review> handle(GetAllReviewsByOfferIdQuery query);
    List<Review> handle(GetAllReviewsByReviewerUserIdQuery query);
    List<Review> handle(GetAllReviewsByAuthorUserIdQuery query);
} 