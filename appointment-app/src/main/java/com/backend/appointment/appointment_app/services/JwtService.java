package com.backend.appointment.appointment_app.services;

import java.util.Date;

import javax.crypto.SecretKey;

import com.backend.appointment.appointment_app.entity.User;

public interface JwtService {
    
    public String extractUsername(String token);

    public String generateToken(final User user);

    public String generateRefreshToken(final User user);

    public String buildToken(final User user, final long expiration);

    public boolean isTokenValid(String token, User user);

    public boolean isTokenExpired(String token);

    public Date extractExpiration(String token);

    public SecretKey getSignInKey();
}
