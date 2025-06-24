package org.nrr.user_service.payload.dto;

import lombok.Data;
import org.nrr.user_service.domain.UserRole;

import java.util.ArrayList;
import java.util.List;

@Data
public class SignupDto {
    private String fullName;
    private String lastName;
    private String email;
    private String password;
    private String username;
    private UserRole role;
    private List<Credential> credentials=new ArrayList<>();
}
