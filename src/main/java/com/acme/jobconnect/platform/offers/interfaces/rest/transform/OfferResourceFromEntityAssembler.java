package com.acme.jobconnect.platform.offers.interfaces.rest.transform;

import com.acme.jobconnect.platform.offers.domain.model.aggregates.Offer;
import com.acme.jobconnect.platform.offers.interfaces.rest.resources.OfferResource;

/**
 * Assembler to convert an {@link Offer} entity to a {@link OfferResource}.
 */
public class OfferResourceFromEntityAssembler {
    /**
     * Converts an {@link Offer} entity to a {@link OfferResource}.
     * @param entity The entity to convert.
     * @return The resource.
     */
    public static OfferResource toResourceFromEntity(Offer entity) {
        return new OfferResource(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getClientId(),
                entity.getClientEmail(),
                entity.getStatus().name(),
                entity.getCategory(),
                entity.getLocation(),
                entity.getBudget(),
                entity.getRequirements(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.getDeadline(),
                entity.getProposalsCount(),
                entity.getSelectedProposalId(),
                entity.getStartAt()
        );
    }
} 