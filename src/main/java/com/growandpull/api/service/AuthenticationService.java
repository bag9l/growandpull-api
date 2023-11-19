package com.growandpull.api.service;

import com.growandpull.api.dto.auth.*;

public interface AuthenticationService {
    AuthenticationResponse registerUser(RegisterRequest request);

    AuthenticationResponse registerAdmin(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);

    AuthenticatedUser getAuthenticatedUser(String login);

    AuthenticationResponse refreshToken(RefreshAuthenticationRequest request);
}
