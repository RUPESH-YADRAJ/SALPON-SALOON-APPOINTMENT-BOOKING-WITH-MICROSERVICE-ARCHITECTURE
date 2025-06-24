package org.nrr.user_service.payload.dto;

import lombok.Data;

import java.util.Map;

@Data
public class KeyCloakRole {
    private String id;
    private String name;
    private String description;
    private Boolean composite;
    private String clientRole;
    private String containerId;
    private Map<String,Object> attributes;
}
