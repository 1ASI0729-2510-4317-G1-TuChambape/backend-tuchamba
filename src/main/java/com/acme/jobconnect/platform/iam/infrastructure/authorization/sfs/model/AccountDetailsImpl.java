package com.acme.jobconnect.platform.iam.infrastructure.authorization.sfs.model;

import com.acme.jobconnect.platform.iam.domain.model.aggregates.Account;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

@Getter
@EqualsAndHashCode
public class AccountDetailsImpl implements UserDetails {

    private final String username; // This is the email
    @JsonIgnore
    private final String password;
    private final boolean accountNonExpired;
    private final boolean accountNonLocked;
    private final boolean credentialsNonExpired;
    private final boolean enabled;
    private final Collection<? extends GrantedAuthority> authorities;

    public AccountDetailsImpl(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
    }

    public static AccountDetailsImpl build(Account account) {
        var authorities = account.getRoles().stream()
                .map(role -> role.getName().name())
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return new AccountDetailsImpl(
                account.getEmail(),
                account.getPassword(),
                authorities);
    }
} 