package org.nrr.salon_service.payload.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SeatDto {

    private Long id;
    private String seatNumber;
}