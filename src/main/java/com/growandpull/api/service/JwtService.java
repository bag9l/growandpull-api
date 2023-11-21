package com.growandpull.api.service;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;
import java.util.Optional;

public interface JwtService {
    Optional<String> extractUsername(String token);

    String generateToken(UserDetails userDetails);

    String generateToken(UserDetails userDetails, long period);

    String generateToken(Map<String, Object> extraClaims,
                         UserDetails userDetails,
                         long period);

    boolean isTokenValid(String token, UserDetails userDetails);
}
