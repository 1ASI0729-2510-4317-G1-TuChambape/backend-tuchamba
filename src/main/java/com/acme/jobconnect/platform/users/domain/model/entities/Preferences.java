package com.acme.jobconnect.platform.users.domain.model.entities;

import com.acme.jobconnect.platform.users.domain.model.valueobjects.PreferredAvailability;
import com.acme.jobconnect.platform.users.domain.model.valueobjects.EstimatedBudgetRange;
import com.acme.jobconnect.platform.users.domain.model.valueobjects.WorkerRating;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "preferences")
@Getter
public class Preferences {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String preferredCategory;
    private String preferredLocation;
    private String preferredServiceType;

    @ElementCollection
    @CollectionTable(name = "preferred_experience_years", joinColumns = @JoinColumn(name = "preferences_id"))
    @Column(name = "experience_years")
    @OrderColumn(name = "idx")
    private List<String> preferredExperienceYears;

    @Enumerated(EnumType.STRING)
    private PreferredAvailability preferredAvailability;

    @Embedded
    private WorkerRating minAcceptableRating;

    @Embedded
    private EstimatedBudgetRange estimatedBudgetRange;

    @ElementCollection
    @CollectionTable(name = "preferred_languages", joinColumns = @JoinColumn(name = "preferences_id"))
    @Column(name = "language")
    @OrderColumn(name = "idx")
    private List<String> languages;

    @ElementCollection
    @CollectionTable(
            name = "offer_skills",
            joinColumns = @JoinColumn(name = "offer_id")
    )
    @Column(name = "skill", nullable = false)
    @OrderColumn(name = "idx")
    private List<String> skills;


    @ElementCollection
    @CollectionTable(
            name = "offer_tools",
            joinColumns = @JoinColumn(name = "offer_id")
    )
    @Column(name = "tool", nullable = false)
    @OrderColumn(name = "idx")
    private List<String> tools;


    @ElementCollection
    @CollectionTable(
            name = "offer_job_modality",
            joinColumns = @JoinColumn(name = "offer_id")
    )
    @Column(name = "modality", nullable = false)
    @OrderColumn(name = "idx")
    private List<String> jobModality;


    @ElementCollection
    @CollectionTable(
            name = "offer_job_experience",
            joinColumns = @JoinColumn(name = "offer_id")
    )
    @Column(name = "experience", nullable = false)
    @OrderColumn(name = "idx")
    private List<String> jobExperience;


    public Preferences() {
        this.skills = new ArrayList<>();
        this.tools = new ArrayList<>();
        this.jobModality = new ArrayList<>();
        this.jobExperience = new ArrayList<>();
    }

    public Preferences(List<String> skills, List<String> tools, List<String> jobModality, List<String> jobExperience) {
        this.skills = skills;
        this.tools = tools;
        this.jobModality = jobModality;
        this.jobExperience = jobExperience;
    }

} 