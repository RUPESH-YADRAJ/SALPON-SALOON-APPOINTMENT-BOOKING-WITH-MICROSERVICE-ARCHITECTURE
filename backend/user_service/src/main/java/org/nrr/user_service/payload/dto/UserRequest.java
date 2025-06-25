package org.nrr.user_service.payload.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class UserRequest {

    private String username;
    private Boolean enabled;
    private String firstName;
    private String lastName;
    private String email;
    private List<Credential> credentials=new ArrayList<>();
    private Map<String, List<String>> attributes = new HashMap<>();
}
