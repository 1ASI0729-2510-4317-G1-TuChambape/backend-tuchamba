package com.acme.jobconnect.platform.offers.interfaces.rest;

import com.acme.jobconnect.platform.offers.domain.model.commands.DeleteReviewCommand;
import com.acme.jobconnect.platform.offers.domain.model.queries.GetAllReviewsByAuthorUserIdQuery;
import com.acme.jobconnect.platform.offers.domain.model.queries.GetAllReviewsByOfferIdQuery;
import com.acme.jobconnect.platform.offers.domain.model.queries.GetAllReviewsByReviewerUserIdQuery;
import com.acme.jobconnect.platform.offers.domain.model.queries.GetReviewByIdQuery;
import com.acme.jobconnect.platform.offers.domain.services.ReviewCommandService;
import com.acme.jobconnect.platform.offers.domain.services.ReviewQueryService;
import com.acme.jobconnect.platform.offers.interfaces.rest.resources.CreateReviewResource;
import com.acme.jobconnect.platform.offers.interfaces.rest.resources.ReviewResource;
import com.acme.jobconnect.platform.offers.interfaces.rest.resources.UpdateReviewResource;
import com.acme.jobconnect.platform.offers.interfaces.rest.transform.CreateReviewCommandFromResourceAssembler;
import com.acme.jobconnect.platform.offers.interfaces.rest.transform.ReviewResourceFromEntityAssembler;
import com.acme.jobconnect.platform.offers.interfaces.rest.transform.UpdateReviewCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name = "Reviews", description = "Review Management Endpoints")
public class ReviewsController {

    private final ReviewCommandService reviewCommandService;
    private final ReviewQueryService reviewQueryService;

    @GetMapping("/api/v1/offers/{offerId}/reviews")
    public ResponseEntity<List<ReviewResource>> getAllReviewsByOfferId(@PathVariable Long offerId) {
        var query = new GetAllReviewsByOfferIdQuery(offerId);
        var reviews = reviewQueryService.handle(query);
        var reviewResources = reviews.stream()
                .map(ReviewResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(reviewResources);
    }

    @GetMapping("/api/v1/reviews/{reviewId}")
    public ResponseEntity<ReviewResource> getReviewById(@PathVariable Long reviewId) {
        var query = new GetReviewByIdQuery(reviewId);
        var review = reviewQueryService.handle(query).orElseThrow();
        var reviewResource = ReviewResourceFromEntityAssembler.toResourceFromEntity(review);
        return ResponseEntity.ok(reviewResource);
    }

    @PutMapping("/api/v1/reviews/{reviewId}")
    public ResponseEntity<ReviewResource> updateReview(@PathVariable Long reviewId, @RequestBody UpdateReviewResource resource) {
        var command = UpdateReviewCommandFromResourceAssembler.toCommandFromResource(reviewId, resource);
        var updatedReviewId = reviewCommandService.handle(command).orElseThrow();
        var updatedReview = reviewQueryService.handle(new GetReviewByIdQuery(updatedReviewId)).orElseThrow();
        var reviewResource = ReviewResourceFromEntityAssembler.toResourceFromEntity(updatedReview);
        return ResponseEntity.ok(reviewResource);
    }

    @DeleteMapping("/api/v1/reviews/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long reviewId) {
        var command = new DeleteReviewCommand(reviewId);
        reviewCommandService.handle(command);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/api/v1/reviews/reviewer/{reviewerUserId}")
    public ResponseEntity<List<ReviewResource>> getAllReviewsByReviewerUserId(@PathVariable Long reviewerUserId) {
        var query = new GetAllReviewsByReviewerUserIdQuery(reviewerUserId);
        var reviews = reviewQueryService.handle(query);
        var reviewResources = reviews.stream()
                .map(ReviewResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(reviewResources);
    }

    @GetMapping("/api/v1/reviews/author/{authorUserId}")
    public ResponseEntity<List<ReviewResource>> getAllReviewsByAuthorUserId(@PathVariable Long authorUserId) {
        var query = new GetAllReviewsByAuthorUserIdQuery(authorUserId);
        var reviews = reviewQueryService.handle(query);
        var reviewResources = reviews.stream()
                .map(ReviewResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(reviewResources);
    }
} 