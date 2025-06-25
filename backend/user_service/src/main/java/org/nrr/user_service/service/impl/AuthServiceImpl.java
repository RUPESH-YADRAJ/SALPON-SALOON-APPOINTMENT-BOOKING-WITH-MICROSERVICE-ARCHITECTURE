package org.nrr.user_service.service.impl;

import lombok.RequiredArgsConstructor;
import org.nrr.user_service.model.User;
import org.nrr.user_service.payload.dto.SignupDto;
import org.nrr.user_service.payload.resopnse.AuthResponse;
import org.nrr.user_service.payload.resopnse.TokenResponse;
import org.nrr.user_service.repository.UserRepository;
import org.nrr.user_service.service.AuthService;
import org.nrr.user_service.service.KeyCloakService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final KeyCloakService keyCloakService;

    public AuthServiceImpl(UserRepository userRepository, KeyCloakService keyCloakService) {
        this.userRepository = userRepository;
        this.keyCloakService = keyCloakService;
    }

    @Override
    public AuthResponse login(String username, String password) throws Exception {
        TokenResponse tokenResponse= keyCloakService.getAdminAccessToken(
                username,
                password,
                "password",
                null
        );

        AuthResponse response=new AuthResponse();
        response.setRefresh_token(tokenResponse.getRefreshToken());
        response.setJwt(tokenResponse.getAccessToken());
        response.setMessage("Login success! ");

        return response;
    }

    @Override
    public AuthResponse signup(SignupDto req) throws Exception {
        keyCloakService.createUser(req);
        User user=new User();
        user.setUsername(req.getUsername());
        user.setPassword(req.getPassword());
        user.setEmail(req.getEmail());
        user.setRole(req.getRole());
        user.setPhoneNumber(req.getPhoneNumber());
        user.setFullName(req.getFullName());
        user.setCreatedAt(LocalDateTime.now());

        userRepository.save(user);

        TokenResponse tokenResponse= keyCloakService.getAdminAccessToken(
                req.getUsername(),
                req.getPassword(),
                "password",
                null
        );

        AuthResponse response=new AuthResponse();
        response.setRefresh_token(tokenResponse.getRefreshToken());
        response.setJwt(tokenResponse.getAccessToken());
        response.setRole(user.getRole());
        response.setMessage("Register success! ");

        return response;
    }

    @Override
    public AuthResponse getAccessTokenFromRefreshToken(String refreshToken) throws Exception {
        TokenResponse tokenResponse= keyCloakService.getAdminAccessToken(
                null,
                null,
                "refresh_token",
                refreshToken
        );

        AuthResponse response=new AuthResponse();
        response.setRefresh_token(tokenResponse.getRefreshToken());
        response.setJwt(tokenResponse.getAccessToken());
        response.setMessage("Login success! ");

        return response;
    }
}
