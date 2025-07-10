package com.acme.jobconnect.platform.iam.domain.model.commands;

import com.acme.jobconnect.platform.iam.domain.model.entities.Role;

import java.util.List;

/**
 * Sign up command
 * <p>
 *     This class represents the command to sign up an account.
 * </p>
 * @param name the name of the account holder
 * @param email the email of the account
 * @param password the password of the account
 * @param roles the roles of the account
 *
 * @see com.acme.jobconnect.platform.iam.domain.model.aggregates.Account
 */
public record SignUpCommand(String name, String email, String password, List<Role> roles) {
}
