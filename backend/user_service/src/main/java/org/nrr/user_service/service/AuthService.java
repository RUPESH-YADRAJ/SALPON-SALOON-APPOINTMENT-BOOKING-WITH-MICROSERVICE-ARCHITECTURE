package org.nrr.user_service.service;

import org.nrr.user_service.payload.dto.SignupDto;
import org.nrr.user_service.payload.resopnse.AuthResponse;

public interface AuthService {
    AuthResponse login(String username, String password) throws Exception;

    AuthResponse signup(SignupDto req) throws Exception;

    AuthResponse getAccessTokenFromRefreshToken(String refreshToken) throws Exception;
}
