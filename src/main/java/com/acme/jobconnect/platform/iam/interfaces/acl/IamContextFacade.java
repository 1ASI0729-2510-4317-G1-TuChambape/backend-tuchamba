package com.acme.jobconnect.platform.iam.interfaces.acl;

import com.acme.jobconnect.platform.iam.domain.model.commands.SignUpCommand;
import com.acme.jobconnect.platform.iam.domain.model.entities.Role;
import com.acme.jobconnect.platform.iam.domain.model.queries.GetAccountByEmailQuery;
import com.acme.jobconnect.platform.iam.domain.model.queries.GetAccountByIdQuery;
import com.acme.jobconnect.platform.iam.domain.services.AccountCommandService;
import com.acme.jobconnect.platform.iam.domain.services.AccountQueryService;
import org.apache.logging.log4j.util.Strings;

import java.util.ArrayList;
import java.util.List;

/**
 * IamContextFacade
 * <p>
 *     This class is a facade for the IAM context. It provides a simple interface for other bounded contexts to interact with the
 *     IAM context.
 *     This class is a part of the ACL layer.
 * </p>
 *
 */
public class IamContextFacade {
    private final AccountCommandService accountCommandService;
    private final AccountQueryService accountQueryService;

    public IamContextFacade(AccountCommandService accountCommandService, AccountQueryService accountQueryService) {
        this.accountCommandService = accountCommandService;
        this.accountQueryService = accountQueryService;
    }

    public Long createAccount(String name, String email, String password) {
        var signUpCommand = new SignUpCommand(name, email, password, List.of(Role.getDefaultRole()));
        var result = accountCommandService.handle(signUpCommand);
        if (result.isEmpty()) return 0L;
        return result.get().getId();
    }

    public Long createAccount(String name, String email, String password, List<String> roleNames) {
        var roles = roleNames != null ? roleNames.stream().map(Role::toRoleFromName).toList() : new ArrayList<Role>();
        var signUpCommand = new SignUpCommand(name, email, password, roles);
        var result = accountCommandService.handle(signUpCommand);
        if (result.isEmpty()) return 0L;
        return result.get().getId();
    }

    public Long fetchAccountIdByEmail(String email) {
        var getAccountByEmailQuery = new GetAccountByEmailQuery(email);
        var result = accountQueryService.handle(getAccountByEmailQuery);
        if (result.isEmpty()) return 0L;
        return result.get().getId();
    }

    public String fetchEmailByAccountId(Long accountId) {
        var getAccountByIdQuery = new GetAccountByIdQuery(accountId);
        var result = accountQueryService.handle(getAccountByIdQuery);
        if (result.isEmpty()) return Strings.EMPTY;
        return result.get().getEmail();
    }
}
