package org.nrr.booking_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookedSeatInfo {
    private Long seatId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
