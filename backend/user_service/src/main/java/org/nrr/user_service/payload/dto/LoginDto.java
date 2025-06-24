package org.nrr.user_service.payload.dto;

import lombok.Data;

@Data
public class LoginDto {
    private String email;
    private String password;
}
