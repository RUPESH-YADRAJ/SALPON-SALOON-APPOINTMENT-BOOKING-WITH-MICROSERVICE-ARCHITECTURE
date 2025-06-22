package org.nrr.service_offering.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {

    private Long id;
    private String fullName;
    private String email;
}
