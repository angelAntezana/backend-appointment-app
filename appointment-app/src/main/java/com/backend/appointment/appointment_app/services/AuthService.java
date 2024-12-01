package com.backend.appointment.appointment_app.services;

import com.backend.appointment.appointment_app.dto.TokenResponse;
import com.backend.appointment.appointment_app.dto.UserDto;
import com.backend.appointment.appointment_app.entity.User;

public interface AuthService {
    public TokenResponse register(final UserDto registerRequest);

    public TokenResponse authenticate(final UserDto loginRequest);

    public void saveUserToken(User user, String jwtToken);

    public void revokeAllUserTokens(final User user);

    public TokenResponse refreshToken(final String authentication);
}
