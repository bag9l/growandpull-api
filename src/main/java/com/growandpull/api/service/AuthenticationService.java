package com.growandpull.api.service;

import com.growandpull.api.dto.auth.*;
import com.growandpull.api.model.entity.User;

public interface AuthenticationService {
    ResponseMessage registerUser(RegisterRequest request);

    ResponseMessage registerAdmin(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);

    AuthenticatedUser getAuthenticatedUser(String login);

    AuthenticationResponse refreshToken(RefreshAuthenticationRequest request);

    AuthenticationResponse confirmEmail(String confirmationToken);

    AuthenticationResponse authenticate(User user);
    AuthenticationResponse resetPassword(String token, String newPassword);
    ResponseMessage sendResetPasswordEmail(String email);

}
