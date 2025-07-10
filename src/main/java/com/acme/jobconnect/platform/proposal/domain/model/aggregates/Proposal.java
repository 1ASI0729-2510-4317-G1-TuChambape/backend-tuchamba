package com.acme.jobconnect.platform.proposal.domain.model.aggregates;

import com.acme.jobconnect.platform.offers.domain.model.aggregates.Offer;
import com.acme.jobconnect.platform.proposal.domain.model.commands.CreateProposalCommand;
import com.acme.jobconnect.platform.proposal.domain.model.valueobjects.Money;
import com.acme.jobconnect.platform.proposal.domain.model.valueobjects.ProposalStatus;
import com.acme.jobconnect.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.acme.jobconnect.platform.users.domain.model.aggregates.Worker;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "proposal")
public class Proposal extends AuditableAbstractAggregateRoot<Proposal> {

    public Proposal(Offer offer, Worker worker, CreateProposalCommand command) {
        this.offer = offer;
        this.worker = worker;
        if(!command.message().isEmpty()){
            this.Message = command.message();
        }
        else{
            throw new IllegalArgumentException("Message cant 't be empty");
        }
        try {
            this.status = ProposalStatus.valueOf(command.status().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Status Proposal invalid: " + command.status() + ". Allowe Value: PENDING, ACCEPTED, REJECTED.");
        }
        this.Price = new Money(command.price());

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "offer_id", nullable = false)
    public Offer offer;

    @ManyToOne
    @JoinColumn(name = "worker_id", nullable = false)
    public Worker worker;

    public String Message;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "price", column = @Column(name = "price"))
    })
    public Money Price;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ProposalStatus status;

    public void accept() {
        this.status = ProposalStatus.ACCEPTED;
    }

    public void reject() {
        this.status = ProposalStatus.REJECTED;
    }

    public Proposal() {
        this.offer = null;
        this.worker = null;
        this.Message = "";
        this.Price = new Money();
        this.status = ProposalStatus.PENDING;
    }
}
