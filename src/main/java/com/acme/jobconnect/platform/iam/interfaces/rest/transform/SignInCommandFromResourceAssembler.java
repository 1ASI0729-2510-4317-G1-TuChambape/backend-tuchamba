package com.acme.jobconnect.platform.iam.interfaces.rest.transform;

import com.acme.jobconnect.platform.iam.domain.model.commands.SignInCommand;
import com.acme.jobconnect.platform.iam.interfaces.rest.resources.SignInRequest;

public class SignInCommandFromResourceAssembler {
    public static SignInCommand toCommandFromResource(SignInRequest signInRequest) {
        return new SignInCommand(signInRequest.email(), signInRequest.password());
    }
}
