package com.acme.jobconnect.platform.iam.infrastructure.authorization.sfs.services;

import com.acme.jobconnect.platform.iam.infrastructure.authorization.sfs.model.AccountDetailsImpl;
import com.acme.jobconnect.platform.iam.infrastructure.persistence.jpa.repositories.AccountRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * This class is responsible for providing the user details to the Spring Security framework.
 * It implements the UserDetailsService interface.
 */
@Service(value = "defaultUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AccountRepository accountRepository;

    public UserDetailsServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /**
     * This method is responsible for loading the user details from the database.
     * @param email The user's email.
     * @return The UserDetails object.
     * @throws UsernameNotFoundException If the user is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var account = accountRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        return AccountDetailsImpl.build(account);
    }
}
