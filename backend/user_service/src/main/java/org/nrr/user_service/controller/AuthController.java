package org.nrr.user_service.controller;

import org.nrr.user_service.payload.dto.LoginDto;
import org.nrr.user_service.payload.dto.SignupDto;
import org.nrr.user_service.payload.resopnse.AuthResponse;
import org.nrr.user_service.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }



    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signup(
            @RequestBody SignupDto signupDto
    ) throws Exception {
        return ResponseEntity.ok(authService.signup(signupDto));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody LoginDto loginDto
    ) throws Exception {
        return ResponseEntity.ok(authService.login(loginDto.getEmail(),loginDto.getPassword()));
    }

    @GetMapping("/access-token/refresh-token/{refreshToken}")
    public ResponseEntity<AuthResponse> getAccessToken(
            @PathVariable String refreshToken
    ) throws Exception {
        return ResponseEntity.ok(authService.getAccessTokenFromRefreshToken(refreshToken));
    }
}
