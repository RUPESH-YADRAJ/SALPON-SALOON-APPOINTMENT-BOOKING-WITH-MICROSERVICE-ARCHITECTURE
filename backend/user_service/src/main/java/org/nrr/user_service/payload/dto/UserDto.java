package org.nrr.user_service.payload.dto;

import lombok.Data;

@Data
public class UserDto {

    private Long id;
    private String fullName;
    private String email;
    private String role;
}