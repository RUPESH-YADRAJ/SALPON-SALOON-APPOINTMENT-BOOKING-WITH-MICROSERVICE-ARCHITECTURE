package org.nrr.salon_service.service.impl;

import org.nrr.salon_service.models.Salon;
import org.nrr.salon_service.payload.dto.SalonDto;
import org.nrr.salon_service.payload.dto.UserDto;
import org.nrr.salon_service.repository.SalonRepository;
import org.nrr.salon_service.service.SalonService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalonServiceImpl implements SalonService {

    private final SalonRepository salonRepository;

    public SalonServiceImpl(SalonRepository salonRepository) {
        this.salonRepository = salonRepository;
    }

    @Override
    public Salon create(SalonDto salonDto, UserDto userDto) {
        Salon salon = Salon.builder()
                .ownerId(userDto.getId())
                .name(salonDto.getName())
                .address(salonDto.getAddress())
                .email(salonDto.getEmail())
                .images(salonDto.getImages())
                .city(salonDto.getCity())
                .startTime(salonDto.getStartTime())
                .closeTime(salonDto.getCloseTime())
                .phoneNumber(salonDto.getPhoneNumber())
                .build();
        return salonRepository.save(salon);
    }

    @Override
    public Salon update(SalonDto salonDto, UserDto userDto, Long salonId) {
        Salon existingSalon = salonRepository.findById(salonId).orElse(null);
        if (existingSalon != null && salonDto.getOwnerId().equals(userDto.getId())) {
            existingSalon.setName(salonDto.getName());
            existingSalon.setAddress(salonDto.getAddress());
            existingSalon.setEmail(salonDto.getEmail());
            existingSalon.setImages(salonDto.getImages());
            existingSalon.setCity(salonDto.getCity());
            existingSalon.setStartTime(salonDto.getStartTime());
            existingSalon.setCloseTime(salonDto.getCloseTime());
            existingSalon.setPhoneNumber(salonDto.getPhoneNumber());
            return salonRepository.save(existingSalon);
        }
        throw new RuntimeException("Salon not found or unauthorized access");
    }

    @Override
    public List<Salon> getAllSalons() {
        return salonRepository.findAll();
    }

    @Override
    public Salon getSalonById(Long salonId) {
        return salonRepository.findById(salonId)
                .orElseThrow(() -> new RuntimeException("Salon not found"));

    }

    @Override
    public Salon getSalonByOwnerId(Long ownerId) {
        return salonRepository.findSalonByOwnerId(ownerId);
    }

    @Override
    public List<Salon> searchSalonByCity(String city) {
        return salonRepository.searchSalon(city);
    }
}
