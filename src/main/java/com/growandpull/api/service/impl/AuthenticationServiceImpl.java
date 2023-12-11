package com.growandpull.api.service.impl;

import com.growandpull.api.dto.auth.*;
import com.growandpull.api.exception.EntityNotExistsException;
import com.growandpull.api.exception.InvalidTokenException;
import com.growandpull.api.mapper.UserMapper;
import com.growandpull.api.model.enums.Role;
import com.growandpull.api.model.entity.User;
import com.growandpull.api.repository.UserRepository;
import com.growandpull.api.service.AuthenticationService;
import com.growandpull.api.service.JwtService;
import com.growandpull.api.service.UserService;
import com.growandpull.api.token.ConfirmationToken;
import com.growandpull.api.token.ConfirmationTokenRepository;
import com.growandpull.api.token.Token;
import com.growandpull.api.token.TokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {
    @Value(value = "${jwt.access_expiration}")
    private long accessExpiration;

    @Value(value = "${jwt.confirm_expiration}")
    private long confirmTokenExpiration;
    @Value(value = "${jwt.refresh_expiration}")
    private int refreshExpiration;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    private final EmailServiceImpl emailService;

    // TODO: check transactionality
    @Transactional
    @Override
    public RegistrationResponse registerUser(RegisterRequest request) {
        return register(request, Role.USER);
    }

    @Transactional
    @Override
    public RegistrationResponse registerAdmin(RegisterRequest request) {
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
    public AuthenticationResponse confirmEmail(String confirmationToken) {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
        User user = userRepository.findUserByEmail(token.getUser().getEmail())
                .orElseThrow(() -> new EntityNotExistsException("User not found"));
        user.setIsEnabled(true);
        user = userRepository.save(user);
        return authenticate(user);

    }

    @Transactional
    @Override
    public AuthenticationResponse authenticate(User user) {
        String accessToken = jwtService.generateToken(user, accessExpiration);
        String refreshToken = jwtService.generateToken(user, refreshExpiration);

        revokeAllUserTokens(user);
        saveUserToken(user, accessToken);

        return new AuthenticationResponse(accessToken, refreshToken);
    }

    private RegistrationResponse register(RegisterRequest request, Role role) {
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

        user = userRepository.save(user);
        String confirmationToken = jwtService.generateToken(user, confirmTokenExpiration);
        saveUserConfirmationToken(user, confirmationToken);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getUsername());
        mailMessage.setSubject("Complete registration!");
        mailMessage.setText("To confirm your account, please click here : "
                + "http://localhost:8000/confirm-account/" + confirmationToken);
        emailService.sendEmail(mailMessage);
        System.out.println("confirmation token" + confirmationToken);
        return new RegistrationResponse("Please check your email for confirmation.");

    }

    @Override
    public AuthenticatedUser getAuthenticatedUser(String email) {
        User user = userService.findUserByEmail(email);
        return userMapper.userToAuthenticatedUser(user);
    }


    private void saveUserToken(User user, String jwtToken) {
        Token token = new Token(jwtToken, false, false, user);
        tokenRepository.save(token);
    }

    private void saveUserConfirmationToken(User user, String jwtToken) {
        ConfirmationToken confirmationToken = new ConfirmationToken(jwtToken, false, false, user);
        confirmationTokenRepository.save(confirmationToken);
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
