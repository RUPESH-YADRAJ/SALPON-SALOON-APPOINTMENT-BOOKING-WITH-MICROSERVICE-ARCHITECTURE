package org.nrr.booking_service.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class BookingSlotDTO {

    LocalDateTime startTime;
    LocalDateTime endTime;
}
