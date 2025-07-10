package com.acme.jobconnect.platform.offers.application.internal.queryservices;

import com.acme.jobconnect.platform.offers.domain.model.entities.Review;
import com.acme.jobconnect.platform.offers.domain.model.queries.GetAllReviewsByAuthorUserIdQuery;
import com.acme.jobconnect.platform.offers.domain.model.queries.GetAllReviewsByOfferIdQuery;
import com.acme.jobconnect.platform.offers.domain.model.queries.GetAllReviewsByReviewerUserIdQuery;
import com.acme.jobconnect.platform.offers.domain.model.queries.GetReviewByIdQuery;
import com.acme.jobconnect.platform.offers.domain.services.ReviewQueryService;
import com.acme.jobconnect.platform.offers.infrastructure.persistence.jpa.repositories.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final ReviewRepository reviewRepository;

    @Override
    public Optional<Review> handle(GetReviewByIdQuery query) {
        return reviewRepository.findById(query.id());
    }

    @Override
    public List<Review> handle(GetAllReviewsByOfferIdQuery query) {
        return reviewRepository.findAllByOfferId(query.offerId());
    }

    @Override
    public List<Review> handle(GetAllReviewsByReviewerUserIdQuery query) {
        return reviewRepository.findAllByReviewerUserId(query.reviewerUserId());
    }

    @Override
    public List<Review> handle(GetAllReviewsByAuthorUserIdQuery query) {
        return reviewRepository.findAllByAuthorUserId(query.authorUserId());
    }
} 