package com.acme.jobconnect.platform.offers.domain.services;

import com.acme.jobconnect.platform.offers.domain.model.aggregates.Offer;
import com.acme.jobconnect.platform.offers.domain.model.queries.GetAllOffersByClientIdQuery;
import com.acme.jobconnect.platform.offers.domain.model.queries.GetAllOffersByStatusAndClientIdQuery;
import com.acme.jobconnect.platform.offers.domain.model.queries.GetAllOffersByStatusQuery;
import com.acme.jobconnect.platform.offers.domain.model.queries.GetOfferByIdQuery;

import java.util.List;
import java.util.Optional;

public interface OfferQueryService {
    Optional<Offer> handle(GetOfferByIdQuery query);
    List<Offer> handle(GetAllOffersByClientIdQuery query);
    List<Offer> handle(GetAllOffersByStatusAndClientIdQuery query);
    List<Offer> handle(GetAllOffersByStatusQuery query);
} 