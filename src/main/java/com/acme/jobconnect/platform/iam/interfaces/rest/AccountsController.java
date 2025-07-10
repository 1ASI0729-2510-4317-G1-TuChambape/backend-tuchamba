package com.acme.jobconnect.platform.iam.interfaces.rest;

import com.acme.jobconnect.platform.iam.domain.model.queries.GetAllAccountsQuery;
import com.acme.jobconnect.platform.iam.domain.model.queries.GetAccountByIdQuery;
import com.acme.jobconnect.platform.iam.domain.model.queries.GetAccountByEmailQuery;
import com.acme.jobconnect.platform.iam.domain.services.AccountQueryService;
import com.acme.jobconnect.platform.iam.interfaces.rest.resources.AccountResource;
import com.acme.jobconnect.platform.iam.interfaces.rest.transform.AccountResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Accounts", description = "Account Management Endpoints")
public class AccountsController {
    private final AccountQueryService accountQueryService;

    public AccountsController(AccountQueryService accountQueryService) {
        this.accountQueryService = accountQueryService;
    }

    @GetMapping
    public ResponseEntity<List<AccountResource>> getAllAccounts() {
        var getAllAccountsQuery = new GetAllAccountsQuery();
        var accounts = accountQueryService.handle(getAllAccountsQuery);
        var accountResources = accounts.stream().map(AccountResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(accountResources);
    }

    @GetMapping(value = "/{accountId}")
    public ResponseEntity<AccountResource> getAccountById(@PathVariable Long accountId) {
        var getAccountByIdQuery = new GetAccountByIdQuery(accountId);
        var account = accountQueryService.handle(getAccountByIdQuery);
        if (account.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var accountResource = AccountResourceFromEntityAssembler.toResourceFromEntity(account.get());
        return ResponseEntity.ok(accountResource);
    }

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<AccountResource> getMyAccount(Principal principal) {
        var getAccountByEmailQuery = new GetAccountByEmailQuery(principal.getName());
        var account = accountQueryService.handle(getAccountByEmailQuery);
        if (account.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var accountResource = AccountResourceFromEntityAssembler.toResourceFromEntity(account.get());
        return ResponseEntity.ok(accountResource);
    }
} 