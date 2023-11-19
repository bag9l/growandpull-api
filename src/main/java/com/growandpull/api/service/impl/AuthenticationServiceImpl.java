package com.growandpull.api.service.impl;

import com.growandpull.api.dto.auth.*;
import com.growandpull.api.exception.InvalidTokenException;
import com.growandpull.api.mapper.UserMapper;
import com.growandpull.api.model.Role;
import com.growandpull.api.model.User;
import com.growandpull.api.repository.UserRepository;
import com.growandpull.api.service.AuthenticationService;
import com.growandpull.api.service.JwtService;
import com.growandpull.api.service.UserService;
import com.growandpull.api.token.Token;
import com.growandpull.api.token.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Value(value = "${jwt.access_expiration}")
    private int accessExpiration;
    @Value(value = "${jwt.refresh_expiration}")
    private int refreshExpiration;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    // TODO: check transactionality
    @Transactional
    @Override
    public AuthenticationResponse registerUser(RegisterRequest request) {
        return register(request, Role.USER);
    }

    @Transactional
    @Override
    public AuthenticationResponse registerAdmin(RegisterRequest request) {
        return register(request, Role.ADMIN);
    }

    @Transactional
    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );

        User user = userService.findUserByEmail(request.email());

        String accessToken = jwtService.generateToken(user, accessExpiration);
        String refreshToken = jwtService.generateToken(user, refreshExpiration);

        revokeAllUserTokens(user);
        saveUserToken(user, accessToken);

        return new AuthenticationResponse(accessToken, refreshToken);
    }

    @Override
    public AuthenticationResponse refreshToken(RefreshAuthenticationRequest request) {
        String token = request.refreshToken().substring("Bearer ".length());
        String username = jwtService.extractUsername(token).orElseThrow(InvalidTokenException::new);

        User user = userService.findUserByEmail(username);

        if (!jwtService.isTokenValid(token, user)) {
            throw new InvalidTokenException();
        }

        String accessToken = jwtService.generateToken(user, accessExpiration);
        String refreshToken = jwtService.generateToken(user, refreshExpiration);

        revokeAllUserTokens(user);
        saveUserToken(user, accessToken);

        return new AuthenticationResponse(accessToken, refreshToken);
    }

    @Override
    public AuthenticatedUser getAuthenticatedUser(String email) {
        User user = userService.findUserByEmail(email);
        return userMapper.userToAuthenticatedUser(user);
    }

    private AuthenticationResponse register(RegisterRequest request, Role role) {

        User user = new User(
                request.email(),
                passwordEncoder.encode(request.password()),
                request.firstName(),
                request.lastName(),
                role,
                null,
                null,
                null
        );

        User savedUser = userRepository.save(user);

        return authenticate(new AuthenticationRequest(request.email(), request.password()));
    }

    private void saveUserToken(User user, String jwtToken) {
        Token token = new Token(jwtToken, false, false, user);
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokensForUser(user.getUsername());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setIsExpired(true);
            token.setIsRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

}
