package org.nrr.salon_service.mapper;

import lombok.Data;
import org.nrr.salon_service.models.Seat;
import org.nrr.salon_service.payload.dto.SeatDto;

@Data
public class SeatMapper {
    public static SeatDto toSeatDto(Seat seat) {
        return SeatDto.builder()
                .id(seat.getId())
                .seatNumber(seat.getSeatNumber())
                .build();
    }
}
