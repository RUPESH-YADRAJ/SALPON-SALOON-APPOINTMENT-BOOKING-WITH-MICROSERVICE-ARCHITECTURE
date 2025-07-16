package org.nrr.booking_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvailableSeatsResponse {
    private Set<Long> availableSeatIds;
    private List<BookedSeatInfo> bookedSeats;
}