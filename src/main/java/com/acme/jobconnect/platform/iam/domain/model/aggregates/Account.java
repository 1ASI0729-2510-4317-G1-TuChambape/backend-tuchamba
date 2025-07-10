package com.acme.jobconnect.platform.iam.domain.model.aggregates;

import com.acme.jobconnect.platform.iam.domain.model.entities.Role;
import com.acme.jobconnect.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Entity(name = "accounts")
public class Account extends AuditableAbstractAggregateRoot<Account> {

    @NotBlank
    @Size(max = 50)
    private String name;

    @NotBlank
    @Size(max = 255)
    @Email
    @Column(unique = true)
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "account_roles",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public Account() {
        this.roles = new HashSet<>();
    }

    public Account(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles = new HashSet<>();
    }

    public Account(String name, String email, String password, List<Role> roles) {
        this(name, email, password);
        addRoles(roles);
    }

    public Account addRole(Role role) {
        this.roles.add(role);
        return this;
    }

    public Account addRoles(List<Role> roles) {
        var validatedRoleSet = Role.validateRoleSet(roles);
        this.roles.addAll(validatedRoleSet);
        return this;
    }
} 