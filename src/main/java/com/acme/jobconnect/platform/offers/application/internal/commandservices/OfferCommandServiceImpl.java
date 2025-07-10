package com.acme.jobconnect.platform.offers.application.internal.commandservices;

import com.acme.jobconnect.platform.offers.domain.model.aggregates.Offer;
import com.acme.jobconnect.platform.offers.domain.model.commands.AddReviewToOfferCommand;
import com.acme.jobconnect.platform.offers.domain.model.commands.CreateOfferCommand;
import com.acme.jobconnect.platform.offers.domain.model.commands.DeleteOfferCommand;
import com.acme.jobconnect.platform.offers.domain.model.commands.UpdateOfferCommand;
import com.acme.jobconnect.platform.offers.domain.model.entities.Review;
import com.acme.jobconnect.platform.offers.domain.model.valueobjects.Rating;
import com.acme.jobconnect.platform.offers.domain.services.OfferCommandService;
import com.acme.jobconnect.platform.offers.infrastructure.persistence.jpa.repositories.OfferRepository;
import com.acme.jobconnect.platform.offers.infrastructure.persistence.jpa.repositories.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OfferCommandServiceImpl implements OfferCommandService {

    private final OfferRepository offerRepository;
    private final ReviewRepository reviewRepository;

    public OfferCommandServiceImpl(OfferRepository offerRepository, ReviewRepository reviewRepository) {
        this.offerRepository = offerRepository;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Optional<Offer> handle(CreateOfferCommand command) {
        if (command.description().length() > 200) {
            throw new IllegalArgumentException("Description cannot be more than 200 characters.");
        }
        var offer = new Offer(command.title(), command.description(), command.clientId(), command.clientEmail(), command.category(), command.location(), command.budget(), command.requirements(), command.deadline(), command.startAt());
        offerRepository.save(offer);
        return Optional.of(offer);
    }

    @Override
    public Optional<Offer> handle(UpdateOfferCommand command) {
        if (command.description() != null && command.description().length() > 200) {
            throw new IllegalArgumentException("Description cannot be more than 200 characters.");
        }
        return offerRepository.findById(command.id()).map(offer -> {
            offer.setTitle(command.title());
            offer.setDescription(command.description());
            offer.setStatus(command.status());
            offer.setCategory(command.category());
            offer.setLocation(command.location());
            offer.setBudget(command.budget());
            offer.setRequirements(command.requirements());
            offer.setStartAt(command.startAt());
            offer.setDeadline(command.deadline());
            offerRepository.save(offer);
            return offer;
        });
    }

    @Override
    public void handle(DeleteOfferCommand command) {
        offerRepository.deleteById(command.offerId());
    }

    @Override
    public Optional<Review> handle(AddReviewToOfferCommand command) {
        return offerRepository.findById(command.offerId()).map(offer -> {
            var rating = new Rating(command.rating());
            var review = new Review(offer, command.authorUserId(), command.reviewerUserId(), command.authorName(), command.authorImageUrl(), command.isVerifiedAuthor(), rating, command.comment());
            reviewRepository.save(review);
            return Optional.of(review);
        }).orElse(Optional.empty());
    }
} 