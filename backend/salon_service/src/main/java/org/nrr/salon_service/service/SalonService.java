package org.nrr.salon_service.service;

import org.nrr.salon_service.models.Salon;
import org.nrr.salon_service.payload.dto.SalonDto;
import org.nrr.salon_service.payload.dto.SeatDto;
import org.nrr.salon_service.payload.dto.UserDto;

import java.util.List;

public interface SalonService {
    Salon create(SalonDto salonDto, UserDto userDto);

    Salon update(SalonDto salonDto, UserDto userDto,Long salonId);

    List<Salon> getAllSalons();

    Salon getSalonById(Long salonId);

    Salon getSalonByOwnerId(Long ownerId);

    List<Salon> searchSalonByCity(String city);

    List<SeatDto> getAllSeatsOfSalon(Long id);
}
