package com.growandpull.api.service;

import com.growandpull.api.dto.auth.AuthenticatedUser;
import com.growandpull.api.dto.auth.AuthenticationRequest;
import com.growandpull.api.dto.auth.AuthenticationResponse;
import com.growandpull.api.dto.auth.RegisterRequest;

public interface AuthenticationService {
    AuthenticationResponse registerUser(RegisterRequest request);

    AuthenticationResponse registerAdmin(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);

    AuthenticatedUser getAuthenticatedUser(String login);
}
