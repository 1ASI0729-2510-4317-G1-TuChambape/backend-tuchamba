package com.acme.jobconnect.platform.profiles.domain.model.queries;

import com.acme.jobconnect.platform.profiles.domain.model.valueobjects.EmailAddress;

/**
 * Get Profile By Email Query
 */
public record GetProfileByEmailQuery(EmailAddress emailAddress) {
}
