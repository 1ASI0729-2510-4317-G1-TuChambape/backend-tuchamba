package com.acme.jobconnect.platform.offers.domain.services;

import com.acme.jobconnect.platform.offers.domain.model.commands.CreateReviewCommand;
import com.acme.jobconnect.platform.offers.domain.model.commands.DeleteReviewCommand;
import com.acme.jobconnect.platform.offers.domain.model.commands.UpdateReviewCommand;

import java.util.Optional;

public interface ReviewCommandService {
    Optional<Long> handle(CreateReviewCommand command);
    Optional<Long> handle(UpdateReviewCommand command);
    void handle(DeleteReviewCommand command);
} 