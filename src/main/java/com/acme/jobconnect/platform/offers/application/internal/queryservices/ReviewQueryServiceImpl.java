package com.acme.jobconnect.platform.offers.application.internal.queryservices;

import com.acme.jobconnect.platform.offers.domain.model.entities.Review;
import com.acme.jobconnect.platform.offers.domain.model.queries.GetAllReviewsQuery;
import com.acme.jobconnect.platform.offers.domain.model.queries.GetReviewByIdQuery;
import com.acme.jobconnect.platform.offers.domain.services.ReviewQueryService;
import com.acme.jobconnect.platform.offers.infrastructure.persistence.jpa.repositories.ReviewRepository;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<Review> handle(GetAllReviewsQuery query) {
        Specification<Review> spec = (root, q, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (query.offerId() != null) {
                predicates.add(cb.equal(root.get("offer").get("id"), query.offerId()));
            }
            if (query.authorUserId() != null) {
                predicates.add(cb.equal(root.get("authorUserId"), query.authorUserId()));
            }
            if (query.reviewerUserId() != null) {
                predicates.add(cb.equal(root.get("reviewerUserId"), query.reviewerUserId()));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
        return reviewRepository.findAll(spec);
    }
} 