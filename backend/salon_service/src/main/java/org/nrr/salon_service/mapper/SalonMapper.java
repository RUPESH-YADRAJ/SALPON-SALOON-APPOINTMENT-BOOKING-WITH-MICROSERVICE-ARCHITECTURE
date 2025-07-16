package org.nrr.salon_service.mapper;

import org.nrr.salon_service.models.Salon;
import org.nrr.salon_service.payload.dto.SalonDto;

public class SalonMapper {
    public static SalonDto toSalonDto(Salon salon) {
        return SalonDto.builder()
                .id(salon.getId())
                .name(salon.getName())
                .address(salon.getAddress())
                .city(salon.getCity())
                .images(salon.getImages())
                .email(salon.getEmail())
                .seatCounts(salon.getSeats().size())
                .phoneNumber(salon.getPhoneNumber())
                .startTime(salon.getStartTime())
                .closeTime(salon.getCloseTime())
                .ownerId(salon.getOwnerId())
                .build();

    }
}
