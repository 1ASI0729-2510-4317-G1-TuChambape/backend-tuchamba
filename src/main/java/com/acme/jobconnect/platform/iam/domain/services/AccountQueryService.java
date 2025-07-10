package com.acme.jobconnect.platform.iam.domain.services;

import com.acme.jobconnect.platform.iam.domain.model.aggregates.Account;
import com.acme.jobconnect.platform.iam.domain.model.queries.GetAccountByEmailQuery;
import com.acme.jobconnect.platform.iam.domain.model.queries.GetAllAccountsQuery;
import com.acme.jobconnect.platform.iam.domain.model.queries.GetAccountByIdQuery;

import java.util.List;
import java.util.Optional;

public interface AccountQueryService {
    List<Account> handle(GetAllAccountsQuery query);
    Optional<Account> handle(GetAccountByIdQuery query);
    Optional<Account> handle(GetAccountByEmailQuery query);
} 