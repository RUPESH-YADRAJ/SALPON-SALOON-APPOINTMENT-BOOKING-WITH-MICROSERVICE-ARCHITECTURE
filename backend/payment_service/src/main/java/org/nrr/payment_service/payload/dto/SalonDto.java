package org.nrr.payment_service.payload.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Data
@Builder
public class SalonDto {

        private Long id;
        private String name;
        private String address;
        private String phoneNumber;
        private String email;
        private String city;
        private boolean isOpen;
        private boolean homeService;
        private boolean active;
        private Long ownerId;
        private LocalTime openTime;
        private LocalTime closeTime;
        private List<String> images;

}
