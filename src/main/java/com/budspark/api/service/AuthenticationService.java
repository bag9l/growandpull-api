package com.budspark.api.service;

import com.budspark.api.dto.auth.AuthenticatedUser;
import com.budspark.api.dto.auth.AuthenticationRequest;
import com.budspark.api.dto.auth.AuthenticationResponse;
import com.budspark.api.dto.auth.RegisterRequest;

public interface AuthenticationService {
    AuthenticationResponse registerUser(RegisterRequest request);

    AuthenticationResponse registerAdmin(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);

    AuthenticatedUser getAuthenticatedUser(String login);
}
