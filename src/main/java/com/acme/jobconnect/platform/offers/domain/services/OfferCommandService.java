package com.acme.jobconnect.platform.offers.domain.services;

import com.acme.jobconnect.platform.offers.domain.model.aggregates.Offer;
import com.acme.jobconnect.platform.offers.domain.model.commands.AddReviewToOfferCommand;
import com.acme.jobconnect.platform.offers.domain.model.commands.CreateOfferCommand;
import com.acme.jobconnect.platform.offers.domain.model.commands.DeleteOfferCommand;
import com.acme.jobconnect.platform.offers.domain.model.commands.UpdateOfferCommand;
import com.acme.jobconnect.platform.offers.domain.model.entities.Review;

import java.util.Optional;

public interface OfferCommandService {
    Optional<Offer> handle(CreateOfferCommand command);
    Optional<Offer> handle(UpdateOfferCommand command);
    void handle(DeleteOfferCommand command);
    Optional<Review> handle(AddReviewToOfferCommand command);
} 