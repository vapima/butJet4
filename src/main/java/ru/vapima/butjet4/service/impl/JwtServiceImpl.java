package ru.vapima.butjet4.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;
import ru.vapima.butjet4.model.db.User;
import ru.vapima.butjet4.service.JwtService;

import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService {

    public static final String SECRET = "SECRETYTY";
    public static final long EXPIRATION_TIME = 3600_000; // 15 mins
    public static final String TOKEN_PREFIX = "Bearer ";


    @Override
    public String createToken(User userDetails) {
        return JWT.create()
                .withSubject(userDetails.getUsername())
                .withClaim("id", userDetails.getId())
                .withClaim("authorities", userDetails.getAuthorities().stream().findFirst().get().getAuthority())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SECRET.getBytes()));
    }


    @Override
    public String takeEmailFromToken(String token) {
        return JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                .build()
                .verify(token.replace(TOKEN_PREFIX, ""))
                .getSubject();
    }

    @Override
    public Long takeIdFromToken(String token) {
        return JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                .build()
                .verify(token.replace(TOKEN_PREFIX, ""))
                .getClaim("id").asLong();
    }

    @Override
    public String takeStringAuthoritiesFromToken(String token) {
        return JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                .build()
                .verify(token.replace(TOKEN_PREFIX, ""))
                .getClaim("authorities").asString();
    }
}