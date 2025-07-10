package com.acme.jobconnect.platform.iam.domain.services;

import com.acme.jobconnect.platform.iam.domain.model.aggregates.Account;
import com.acme.jobconnect.platform.iam.domain.model.commands.SignInCommand;
import com.acme.jobconnect.platform.iam.domain.model.commands.SignUpCommand;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Optional;

public interface AccountCommandService {
    Optional<ImmutablePair<Account, String>> handle(SignInCommand command);
    Optional<Account> handle(SignUpCommand command);
} 