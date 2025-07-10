package com.acme.jobconnect.platform.offers.domain.services;

import com.acme.jobconnect.platform.offers.domain.model.aggregates.Offer;
import com.acme.jobconnect.platform.offers.domain.model.queries.GetAllOffersQuery;
import com.acme.jobconnect.platform.offers.domain.model.queries.GetOfferByIdQuery;

import java.util.List;
import java.util.Optional;

public interface OfferQueryService {
    List<Offer> handle(GetAllOffersQuery query);
    Optional<Offer> handle(GetOfferByIdQuery query);
} 