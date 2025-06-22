package org.nrr.booking_service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ServiceDto {

    private Long id;

    private String name;


    private String description;


    private int price;


    private int duration;


    private Long category;

    private String image;
}
