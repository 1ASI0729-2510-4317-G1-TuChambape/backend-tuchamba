package com.acme.jobconnect.platform.proposal.infrastructure.persistence.jpa.repositories;

import com.acme.jobconnect.platform.proposal.domain.model.aggregates.Proposal;
import com.acme.jobconnect.platform.proposal.domain.model.valueobjects.ProposalStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProposalRepository extends JpaRepository<Proposal, Long> {
    List<Proposal> findByOffer_Id(Long offerId);
    List<Proposal> findByWorker_Id(Long workerId);
    List<Proposal> findByStatus(ProposalStatus status);
}
