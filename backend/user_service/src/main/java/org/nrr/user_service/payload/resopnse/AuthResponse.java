package org.nrr.user_service.payload.resopnse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.nrr.user_service.domain.UserRole;

@Data
public class AuthResponse {
    private String jwt;
    private String refresh_token;
    private String message;
    private String title;
    private UserRole role;
}
