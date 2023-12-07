package com.growandpull.api.service;

import com.growandpull.api.dto.auth.*;
import com.growandpull.api.model.User;

public interface AuthenticationService {
    RegistrationResponse registerUser(RegisterRequest request);

    RegistrationResponse registerAdmin(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);

    AuthenticatedUser getAuthenticatedUser(String login);

    AuthenticationResponse refreshToken(RefreshAuthenticationRequest request);

    AuthenticationResponse confirmEmail(String confirmationToken);

    AuthenticationResponse authenticate(User user);

}
