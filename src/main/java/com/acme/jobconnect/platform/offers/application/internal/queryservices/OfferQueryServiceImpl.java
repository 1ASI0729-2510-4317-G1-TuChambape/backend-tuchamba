package com.acme.jobconnect.platform.offers.application.internal.queryservices;

import com.acme.jobconnect.platform.offers.domain.model.aggregates.Offer;
import com.acme.jobconnect.platform.offers.domain.model.queries.GetAllOffersQuery;
import com.acme.jobconnect.platform.offers.domain.model.queries.GetOfferByIdQuery;
import com.acme.jobconnect.platform.offers.domain.services.OfferQueryService;
import com.acme.jobconnect.platform.offers.infrastructure.persistence.jpa.repositories.OfferRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OfferQueryServiceImpl implements OfferQueryService {

    private final OfferRepository offerRepository;

    public OfferQueryServiceImpl(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public List<Offer> handle(GetAllOffersQuery query) {
        return offerRepository.findAll();
    }

    @Override
    public Optional<Offer> handle(GetOfferByIdQuery query) {
        return offerRepository.findById(query.offerId());
    }
} 