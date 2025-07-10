package com.acme.jobconnect.platform.users.domain.model.aggregates;

import com.acme.jobconnect.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "users")
@Getter
public class User extends AuditableAbstractAggregateRoot<User> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long accountId;

    @OneToOne
    @JoinColumn(name = "worker_id")
    private Worker worker;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public User(Long accountId) {
        this.accountId = accountId;
    }

    public User(Long accountId, Worker worker, Customer customer) {
        this.accountId = accountId;
        this.worker = worker;
        this.customer = customer;
    }

    public User() {

    }
} 