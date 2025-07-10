package com.acme.jobconnect.platform.iam.application.internal.commandservices;

import com.acme.jobconnect.platform.iam.application.internal.outboundservices.hashing.HashingService;
import com.acme.jobconnect.platform.iam.application.internal.outboundservices.tokens.TokenService;
import com.acme.jobconnect.platform.iam.domain.model.aggregates.Account;
import com.acme.jobconnect.platform.iam.domain.model.commands.SignInCommand;
import com.acme.jobconnect.platform.iam.domain.model.commands.SignUpCommand;
import com.acme.jobconnect.platform.iam.domain.services.AccountCommandService;
import com.acme.jobconnect.platform.iam.infrastructure.persistence.jpa.repositories.AccountRepository;
import com.acme.jobconnect.platform.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountCommandServiceImpl implements AccountCommandService {

    private final AccountRepository accountRepository;
    private final HashingService hashingService;
    private final TokenService tokenService;
    private final RoleRepository roleRepository;

    public AccountCommandServiceImpl(AccountRepository accountRepository, HashingService hashingService, TokenService tokenService, RoleRepository roleRepository) {
        this.accountRepository = accountRepository;
        this.hashingService = hashingService;
        this.tokenService = tokenService;
        this.roleRepository = roleRepository;
    }

    @Override
    public Optional<ImmutablePair<Account, String>> handle(SignInCommand command) {
        var account = accountRepository.findByEmail(command.email());
        if (account.isEmpty()) {
            throw new RuntimeException("Account not found");
        }
        if (!hashingService.matches(command.password(), account.get().getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        var token = tokenService.generateToken(account.get().getEmail());
        return Optional.of(ImmutablePair.of(account.get(), token));
    }

    @Override
    public Optional<Account> handle(SignUpCommand command) {
        if (accountRepository.existsByEmail(command.email())) {
            throw new RuntimeException("An account with this email already exists");
        }
        var roles = command.roles().stream()
                .map(role -> roleRepository.findByName(role.getName())
                        .orElseThrow(() -> new RuntimeException("Role name not found")))
                .toList();
        var account = new Account(command.name(), command.email(), hashingService.encode(command.password()), roles);
        accountRepository.save(account);
        return accountRepository.findByEmail(command.email());
    }
} 