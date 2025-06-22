package org.nrr.payment_service.payload.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class BookingSlotDto {

    LocalDateTime startTime;
    LocalDateTime endTime;
}
