package org.nrr.booking_service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SeatDto {

    private Long id;
    private String seatNumber;
}