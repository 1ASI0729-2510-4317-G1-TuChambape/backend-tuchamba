package com.acme.jobconnect.platform.iam.application.internal.queryservices;

import com.acme.jobconnect.platform.iam.domain.model.aggregates.Account;
import com.acme.jobconnect.platform.iam.domain.model.queries.GetAllAccountsQuery;
import com.acme.jobconnect.platform.iam.domain.model.queries.GetAccountByEmailQuery;
import com.acme.jobconnect.platform.iam.domain.model.queries.GetAccountByIdQuery;
import com.acme.jobconnect.platform.iam.domain.services.AccountQueryService;
import com.acme.jobconnect.platform.iam.infrastructure.persistence.jpa.repositories.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountQueryServiceImpl implements AccountQueryService {

    private final AccountRepository accountRepository;

    public AccountQueryServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Account> handle(GetAllAccountsQuery query) {
        return accountRepository.findAll();
    }

    @Override
    public Optional<Account> handle(GetAccountByIdQuery query) {
        return accountRepository.findById(query.id());
    }

    @Override
    public Optional<Account> handle(GetAccountByEmailQuery query) {
        return accountRepository.findByEmail(query.email());
    }
} 