package com.acme.jobconnect.platform.offers.application.internal.commandservices;

import com.acme.jobconnect.platform.offers.domain.model.commands.CreateReviewCommand;
import com.acme.jobconnect.platform.offers.domain.model.commands.DeleteReviewCommand;
import com.acme.jobconnect.platform.offers.domain.model.commands.UpdateReviewCommand;
import com.acme.jobconnect.platform.offers.domain.model.entities.Review;
import com.acme.jobconnect.platform.offers.domain.model.valueobjects.Rating;
import com.acme.jobconnect.platform.offers.domain.services.ReviewCommandService;
import com.acme.jobconnect.platform.offers.infrastructure.persistence.jpa.repositories.OfferRepository;
import com.acme.jobconnect.platform.offers.infrastructure.persistence.jpa.repositories.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final ReviewRepository reviewRepository;
    private final OfferRepository offerRepository;

    @Override
    public Optional<Long> handle(CreateReviewCommand command) {
        var offer = offerRepository.findById(command.offerId())
                .orElseThrow(() -> new IllegalArgumentException("Offer with id " + command.offerId() + " not found"));
        var rating = new Rating(command.rating());
        var review = new Review(
                offer,
                command.authorUserId(),
                command.reviewerUserId(),
                command.authorName(),
                command.authorImageUrl(),
                command.isVerifiedAuthor(),
                rating,
                command.comment()
        );
        reviewRepository.save(review);
        return Optional.of(review.getId());
    }

    @Override
    public Optional<Long> handle(UpdateReviewCommand command) {
        var review = reviewRepository.findById(command.id())
                .orElseThrow(() -> new IllegalArgumentException("Review with id " + command.id() + " not found"));

        if (command.rating() != null) {
            var rating = new Rating(command.rating());
            review.setRating(rating);
        }

        if (command.comment() != null) {
            review.setComment(command.comment());
        }

        reviewRepository.save(review);
        return Optional.of(review.getId());
    }

    @Override
    public void handle(DeleteReviewCommand command) {
        if (!reviewRepository.existsById(command.id())) {
            throw new IllegalArgumentException("Review with id " + command.id() + " not found");
        }
        reviewRepository.deleteById(command.id());
    }
} 