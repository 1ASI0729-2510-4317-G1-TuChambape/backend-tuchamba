package com.acme.jobconnect.platform.proposal.application.internal.queryservices;

import com.acme.jobconnect.platform.proposal.domain.model.aggregates.Proposal;
import com.acme.jobconnect.platform.proposal.domain.model.queries.*;
import com.acme.jobconnect.platform.proposal.domain.services.ProposalQueryService;
import com.acme.jobconnect.platform.proposal.infrastructure.persistence.jpa.repositories.ProposalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProposalQueryServiceImpl implements ProposalQueryService {

    private final ProposalRepository repository;

    public ProposalQueryServiceImpl(ProposalRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Proposal> handle(GetAllProposalByOfferIdQuery query) {
        return repository.findByOffer_Id(query.offerId());
    }

    @Override
    public List<Proposal> handle(GetAllProposalByStatusQuery query) {
        return repository.findByStatus(query.status());
    }

    @Override
    public List<Proposal> handle(GetAllProposalByWorkerIdQuery query) {
        return repository.findByWorker_Id(query.workerId());
    }

    @Override
    public List<Proposal> handle(GetAllProposalsQuery query) {
        return repository.findAll();
    }

    @Override
    public Optional<Proposal> handle(GetProposalByIdQuery query) {
        return repository.findById(query.Id());
    }
}
