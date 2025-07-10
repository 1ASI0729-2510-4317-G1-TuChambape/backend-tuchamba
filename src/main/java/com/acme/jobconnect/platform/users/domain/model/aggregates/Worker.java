package com.acme.jobconnect.platform.users.domain.model.aggregates;

import com.acme.jobconnect.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.acme.jobconnect.platform.users.domain.model.entities.Availability;
import com.acme.jobconnect.platform.users.domain.model.valueobjects.WorkerRating;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import java.util.List;

@Entity
@Table(name = "workers")
@Getter
@Setter
public class Worker extends AuditableAbstractAggregateRoot<Worker> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @Size(max = 50)
    private String firstName;

    @Size(max = 50)
    private String lastName;

    @Pattern(regexp = "[0-9]{9}")
    private String phone;

    @URL
    private String avatar;

    private String location;

    @Column(length = 500)
    private String bio;

    @ElementCollection
    @CollectionTable(name = "worker_skills", joinColumns = @JoinColumn(name = "worker_id"))
    @Column(name = "skill")
    @OrderColumn(name = "idx")
    private List<String> skills;

    private Integer experience;

    @ElementCollection
    @CollectionTable(name = "worker_certifications", joinColumns = @JoinColumn(name = "worker_id"))
    @Column(name = "certification")
    @OrderColumn(name = "idx")
    private List<String> certifications;

    @Embedded
    private WorkerRating rating;

    private Integer reviewCount;

    private boolean isVerified;

    @Embedded
    private Availability availability;

    @Pattern(regexp = "[0-9]{9}")
    private String yapeNumber;

    @Pattern(regexp = "[0-9]{9}")
    private String plinNumber;

    @Pattern(regexp = "[0-9]{20}")
    private String bankAccountNumber;

    public Worker() {}

    public Worker(String email, String firstName, String lastName, String phone, String avatar, String location, String bio, List<String> skills, Integer experience, List<String> certifications, String monday, String tuesday, String wednesday, String thursday, String friday, String saturday, String sunday, String yapeNumber, String plinNumber, String bankAccountNumber) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.avatar = avatar;
        this.location = location;
        this.bio = bio;
        this.skills = skills;
        this.experience = experience;
        this.certifications = certifications;
        this.rating = new WorkerRating(0.0);
        this.reviewCount = 0;
        this.isVerified = false;
        this.availability = new Availability(monday, tuesday, wednesday, thursday, friday, saturday, sunday);
        this.yapeNumber = yapeNumber;
        this.plinNumber = plinNumber;
        this.bankAccountNumber = bankAccountNumber;
    }
} 