package com.acme.jobconnect.platform.offers.domain.model.queries;

public record GetAllReviewsQuery(Long offerId, Long authorUserId, Long reviewerUserId) {
} 