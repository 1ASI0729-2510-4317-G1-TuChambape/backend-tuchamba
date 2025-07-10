package com.acme.jobconnect.platform.offers.domain.model.entities;

import com.acme.jobconnect.platform.offers.domain.model.aggregates.Offer;
import com.acme.jobconnect.platform.offers.domain.model.valueobjects.Rating;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "reviews")
@Getter
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "offer_id", nullable = false)
    private Offer offer;

    private Long authorUserId;
    private Long reviewerUserId;
    private String authorName;
    private String authorImageUrl;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    private boolean isVerifiedAuthor;

    @Embedded
    @Setter
    private Rating rating;

    @Size(max = 1000) // Approx 150 words as per requirement
    @Setter
    private String comment;

    public Review() {}

    public Review(Offer offer, Long authorUserId, Long reviewerUserId, String authorName, String authorImageUrl, boolean isVerifiedAuthor, Rating rating, String comment) {
        this.offer = offer;
        this.authorUserId = authorUserId;
        this.reviewerUserId = reviewerUserId;
        this.authorName = authorName;
        this.authorImageUrl = authorImageUrl;
        this.isVerifiedAuthor = isVerifiedAuthor;
        this.rating = rating;
        this.comment = comment;
        this.createdAt = new Date();
    }
} 