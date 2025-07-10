package com.acme.jobconnect.platform.offers.application.internal.queryservices;

import com.acme.jobconnect.platform.offers.domain.model.aggregates.Offer;
import com.acme.jobconnect.platform.offers.domain.model.queries.GetAllOffersByClientIdQuery;
import com.acme.jobconnect.platform.offers.domain.model.queries.GetAllOffersByStatusAndClientIdQuery;
import com.acme.jobconnect.platform.offers.domain.model.queries.GetAllOffersByStatusQuery;
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
    public Optional<Offer> handle(GetOfferByIdQuery query) {
        return offerRepository.findById(query.offerId());
    }

    @Override
    public List<Offer> handle(GetAllOffersByClientIdQuery query) {
        return offerRepository.findAllByClientId(query.clientId());
    }

    @Override
    public List<Offer> handle(GetAllOffersByStatusAndClientIdQuery query) {
        return offerRepository.findAllByStatusAndClientId(query.status(), query.clientId());
    }

    @Override
    public List<Offer> handle(GetAllOffersByStatusQuery query) {
        return offerRepository.findAllByStatus(query.status());
    }
} 