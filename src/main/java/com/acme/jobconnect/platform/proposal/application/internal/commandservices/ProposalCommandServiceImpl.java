package com.acme.jobconnect.platform.proposal.application.internal.commandservices;

import com.acme.jobconnect.platform.offers.domain.model.queries.GetOfferByIdQuery;
import com.acme.jobconnect.platform.offers.infrastructure.persistence.jpa.repositories.OfferRepository;
import com.acme.jobconnect.platform.proposal.domain.model.aggregates.Proposal;
import com.acme.jobconnect.platform.proposal.domain.model.commands.*;
import com.acme.jobconnect.platform.proposal.domain.model.valueobjects.Money;
import com.acme.jobconnect.platform.proposal.domain.model.valueobjects.ProposalStatus;
import com.acme.jobconnect.platform.proposal.domain.services.ProposalCommandService;
import com.acme.jobconnect.platform.proposal.infrastructure.persistence.jpa.repositories.ProposalRepository;
import com.acme.jobconnect.platform.users.infrastructure.persistence.jpa.repositories.WorkerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProposalCommandServiceImpl implements ProposalCommandService {

    private final ProposalRepository repository;
    private final WorkerRepository workerRepository;
    private final OfferRepository offerRepository;

    public ProposalCommandServiceImpl(ProposalRepository repository,
                                      WorkerRepository workerRepository,
                                      OfferRepository offerRepository) {
        this.repository = repository;
        this.workerRepository =  workerRepository;
        this.offerRepository = offerRepository;
    }

    @Override
    public Optional<Proposal> handle(CreateProposalCommand command) {
        var offer = offerRepository.findById(command.offerId());
        var worker = workerRepository.findById(command.workerId());

        if (offer.isEmpty() || worker.isEmpty()) {
            return Optional.empty();
        }
        var proposal = new Proposal(offer.get(),worker.get(),command);

        repository.save(proposal);

        return Optional.of(proposal);
    }

    @Override
    public void handle(DeleteProposalByIdCommand command) {
        var proposal = repository.findById(command.id());
        if (proposal.isEmpty()) {
            return;
        }
        repository.delete(proposal.get());

    }

    @Override
    public Optional<Proposal> handle(UpdateProposalCommand command) {
        var proposal = repository.findById(command.id())
                .orElseThrow(() -> new IllegalArgumentException("Proposal with id " + command.id() + " not found"));
        if (command.message() != null && !command.message().isBlank()) {
            proposal.setMessage(command.message());
        }
        proposal.setPrice(new Money(command.price()));
        repository.save(proposal);
        return Optional.of(proposal);
    }

    @Override
    public Optional<Proposal> handle(AcceptProposalCommand command) {
        var proposal = repository.findById(command.id())
                .orElseThrow(() -> new IllegalArgumentException("Proposal with id " + command.id() + " not found"));
        proposal.accept();
        repository.save(proposal);
        return Optional.of(proposal);
    }

    @Override
    public Optional<Proposal> handle(RejectProposalCommand command) {
        var proposal = repository.findById(command.id())
                .orElseThrow(() -> new IllegalArgumentException("Proposal with id " + command.id() + " not found"));
        proposal.reject();
        repository.save(proposal);
        return Optional.of(proposal);
    }
}
