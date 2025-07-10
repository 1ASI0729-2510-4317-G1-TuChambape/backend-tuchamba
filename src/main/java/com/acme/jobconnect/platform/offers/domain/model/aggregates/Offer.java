package com.acme.jobconnect.platform.offers.domain.model.aggregates;

import com.acme.jobconnect.platform.offers.domain.model.commands.CreateOfferCommand;
import com.acme.jobconnect.platform.offers.domain.model.commands.UpdateOfferDetailsCommand;
import com.acme.jobconnect.platform.offers.domain.model.entities.Review;
import com.acme.jobconnect.platform.offers.domain.model.valueobjects.Budget;
import com.acme.jobconnect.platform.offers.domain.model.valueobjects.OfferStatus;
import com.acme.jobconnect.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "offers")
@Getter
@Setter
public class Offer extends AuditableAbstractAggregateRoot<Offer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 100)
    private String title;

    @Size(max = 200)
    private String description;

    private Long clientId;
    private String clientEmail;

    @Enumerated(EnumType.STRING)
    private OfferStatus status;

    private String category;
    private String location;

    @Embedded
    private Budget budget;

    @ElementCollection
    @CollectionTable(
            name = "offer_requirements",
            joinColumns = @JoinColumn(name = "offer_id") // FK a la tabla Offer
    )
    @Column(name = "requirement", nullable = false)
    @OrderColumn(name = "idx")
    private List<String> requirements = new ArrayList<>();

    @OneToMany(mappedBy = "offer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    private Date deadline;
    private Date startAt;
    private Integer proposalsCount;
    private Long selectedProposalId;

    public Offer(String title, String description, Long aLong, String s, String category, String location, Budget budget, List<String> requirements, Date deadline, Date date) {}

    public Offer(CreateOfferCommand command) {
        this.title = command.title();
        this.description = command.description();
        this.clientId = command.clientId();
        this.clientEmail = command.clientEmail();
        this.status = OfferStatus.PENDING;
        this.category = command.category();
        this.location = command.location();
        this.budget = command.budget();
        this.requirements = command.requirements();
        this.startAt = command.startAt();
        this.deadline = command.deadline();
        this.proposalsCount = 0;
    }

    public Offer() {

    }

    public void updateDetails(UpdateOfferDetailsCommand command) {
        this.title = command.title();
        this.description = command.description();
        this.status = command.status();
        this.category = command.category();
        this.location = command.location();
        this.budget = command.budget();
        this.requirements = command.requirements();
        this.startAt = command.startAt();
        this.deadline = command.deadline();
    }


    public void addReview(Review review) {
        this.reviews.add(review);
    }
} 