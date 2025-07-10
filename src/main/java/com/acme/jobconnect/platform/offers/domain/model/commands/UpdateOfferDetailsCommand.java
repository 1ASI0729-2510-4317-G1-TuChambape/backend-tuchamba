package com.acme.jobconnect.platform.offers.domain.model.commands;

import com.acme.jobconnect.platform.offers.domain.model.valueobjects.OfferStatus;
import com.acme.jobconnect.platform.offers.domain.model.valueobjects.Budget;
import java.util.Date;
import java.util.List;

public record UpdateOfferDetailsCommand(
    String title,
    String description,
    OfferStatus status,
    String category,
    String location,
    Budget budget,
    List<String> requirements,
    Date deadline,
    Date startAt
) {} 