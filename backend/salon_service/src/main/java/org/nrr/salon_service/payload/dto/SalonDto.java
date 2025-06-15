package org.nrr.salon_service.payload.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Data
@Builder
public class SalonDto {


    private Long id;


    private String name;


    private List<String> images;


    private String address;


    private String phoneNumber;


    private String email;


    private String city;


    private Long ownerId;


    private LocalTime startTime;


    private LocalTime closeTime;
}
