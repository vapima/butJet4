package ru.vapima.butjet4.service;

import ru.vapima.butjet4.model.db.User;

public interface JwtService {
    String createToken(User userDetails);

    String takeEmailFromToken(String token);

    Long takeIdFromToken(String token);

    String takeStringAuthoritiesFromToken(String token);
}
