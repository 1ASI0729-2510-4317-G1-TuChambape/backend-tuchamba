package com.acme.jobconnect.platform.users.domain.model.aggregates;

import com.acme.jobconnect.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.acme.jobconnect.platform.users.domain.model.entities.Preferences;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Entity
@Table(name = "customers")
@Getter
public class Customer extends AuditableAbstractAggregateRoot<Customer> {
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

    private String location;

    @Column(length = 500)
    private String bio;

    private boolean isVerified;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "preferences_id")
    private Preferences preferences;

    public Customer() {
    }

    public Customer(String email, String firstName, String lastName, String phone, String location, String bio) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.location = location;
        this.bio = bio;
        this.isVerified = false; // Default value
        this.preferences = new Preferences(); // Default value
    }
} 