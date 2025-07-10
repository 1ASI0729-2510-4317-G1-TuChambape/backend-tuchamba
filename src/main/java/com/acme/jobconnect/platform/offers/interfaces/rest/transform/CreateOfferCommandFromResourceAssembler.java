package com.acme.jobconnect.platform.offers.interfaces.rest.transform;

import com.acme.jobconnect.platform.offers.domain.model.commands.CreateOfferCommand;
import com.acme.jobconnect.platform.offers.interfaces.rest.resources.CreateOfferResource;

/**
 * Assembler to convert a {@link CreateOfferResource} to a {@link CreateOfferCommand}.
 * <p>
 *     This class is responsible for converting a {@link CreateOfferResource} object, which is used in the presentation layer,
 *     to a {@link CreateOfferCommand} object, which is used in the domain layer.
 * </p>
 */
public class CreateOfferCommandFromResourceAssembler {
    /**
     * Converts a {@link CreateOfferResource} to a {@link CreateOfferCommand}.
     * @param resource The resource to convert.
     * @return The command.
     */
    public static CreateOfferCommand toCommandFromResource(CreateOfferResource resource) {
        return new CreateOfferCommand(
                resource.title(),
                resource.description(),
                resource.clientId(),
                resource.clientEmail(),
                resource.category(),
                resource.location(),
                resource.budget(),
                resource.requirements(),
                resource.deadline(),
                resource.startAt()
        );
    }
} 