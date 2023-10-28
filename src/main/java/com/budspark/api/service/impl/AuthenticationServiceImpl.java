package com.budspark.api.service.impl;

import com.budspark.api.dto.auth.AuthenticatedUser;
import com.budspark.api.dto.auth.AuthenticationRequest;
import com.budspark.api.dto.auth.AuthenticationResponse;
import com.budspark.api.dto.auth.RegisterRequest;
import com.budspark.api.mapper.UserMapper;
import com.budspark.api.model.Role;
import com.budspark.api.model.User;
import com.budspark.api.repository.UserRepository;
import com.budspark.api.service.AuthenticationService;
import com.budspark.api.service.JwtService;
import com.budspark.api.service.UserService;
import com.budspark.api.token.Token;
import com.budspark.api.token.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;


    @Override
    public AuthenticationResponse registerUser(RegisterRequest request) {
        return register(request, Role.USER);
    }

    @Override
    public AuthenticationResponse registerAdmin(RegisterRequest request) {
        return register(request, Role.ADMIN);
    }

    @Transactional
    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.login(),
                        request.password()
                )
        );

        User user = userService.findUserByLogin(request.login());

        String jwt = jwtService.generateToken(user);

        revokeAllUserTokens(user);
        saveUserToken(user, jwt);

        return new AuthenticationResponse(jwt);
    }

    @Override
    public AuthenticatedUser getAuthenticatedUser(String login) {
        User user = userService.findUserByLogin(login);
        return userMapper.userToAuthenticatedUser(user);
    }

    public AuthenticationResponse register(RegisterRequest request, Role role) {

        User user = new User(
                request.login(),
                passwordEncoder.encode(request.password()),
                request.fullName(),
                request.email(),
                role,
                null,
                null
        );

        User savedUser = userRepository.save(user);
        String jwt = jwtService.generateToken(user);

        saveUserToken(savedUser, jwt);

        return new AuthenticationResponse(jwt);
    }


    private void saveUserToken(User user, String jwtToken) {
        Token token = new Token(jwtToken, false, false, user);
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokensForUser(user.getLogin());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setIsExpired(true);
            token.setIsRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

}
