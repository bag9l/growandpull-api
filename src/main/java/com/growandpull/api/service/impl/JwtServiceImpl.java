package com.growandpull.api.service.impl;

import com.growandpull.api.exception.InvalidTokenException;
import com.growandpull.api.service.JwtService;
import com.growandpull.api.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class JwtServiceImpl implements JwtService {
    @Value(value = "${jwt.secret}")
    private String secretKey;

    @Value(value = "${jwt.access_expiration}")
    private long defaultTokenExpiration;

    private final UserService userService;

    @Override
    public Optional<String> extractUsername(String token) {
        return Optional.of(extractClaim(token, Claims::getSubject));
    }

    @Override
    public String generateToken(UserDetails userDetails) {
        return generateToken(userDetails, defaultTokenExpiration);
    }

    @Override
    public String generateToken(UserDetails userDetails, long period) {
        Map<String, Object> claims = new HashMap<>();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        String id = userService.getUserIdByEmail(userDetails.getUsername());

        claims.put("roles", roles);
        claims.put("id", id);

        return generateToken(claims, userDetails, period);
    }

    @Override
    public String generateToken(Map<String, Object> extraClaims,
                                UserDetails userDetails,
                                long period) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + period))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        String username = extractUsername(token).orElseThrow(InvalidTokenException::new);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private boolean isTokenExpired(String token) {
        return getTokenExpiration(token).before(new Date());
    }

    private Date getTokenExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
