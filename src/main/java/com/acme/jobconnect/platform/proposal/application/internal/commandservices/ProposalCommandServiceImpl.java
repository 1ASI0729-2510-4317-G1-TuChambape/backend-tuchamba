package com.acme.jobconnect.platform.proposal.application.internal.commandservices;

import com.acme.jobconnect.platform.offers.domain.model.queries.GetOfferByIdQuery;
import com.acme.jobconnect.platform.offers.infrastructure.persistence.jpa.repositories.OfferRepository;
import com.acme.jobconnect.platform.proposal.domain.model.aggregates.Proposal;
import com.acme.jobconnect.platform.proposal.domain.model.commands.CreateProposalCommand;
import com.acme.jobconnect.platform.proposal.domain.model.commands.DeleteProposalByIdCommand;
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
}
