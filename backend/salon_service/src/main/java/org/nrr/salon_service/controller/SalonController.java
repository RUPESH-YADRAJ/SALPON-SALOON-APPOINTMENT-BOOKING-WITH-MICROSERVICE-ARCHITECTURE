package org.nrr.salon_service.controller;


import org.nrr.salon_service.mapper.SalonMapper;
import org.nrr.salon_service.models.Salon;
import org.nrr.salon_service.payload.dto.SalonDto;
import org.nrr.salon_service.payload.dto.UserDto;
import org.nrr.salon_service.service.SalonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salons")
public class SalonController {
    private final SalonService salonService;

    public SalonController(SalonService salonService) {
        this.salonService = salonService;
    }


    @PostMapping()
    public ResponseEntity<SalonDto> createSalon(@RequestBody SalonDto salonDto) {
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        Salon salon=salonService.create(salonDto,userDto);
        return ResponseEntity.ok(SalonMapper.toSalonDto(salon));
    }

    @PatchMapping("/{salonId}")
    public ResponseEntity<SalonDto> updateSalon(@PathVariable Long salonId,@RequestBody SalonDto salonDto) {
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        Salon salon=salonService.update(salonDto,userDto,salonId);
        return ResponseEntity.ok(SalonMapper.toSalonDto(salon));
    }

    @GetMapping()
    public ResponseEntity<List<SalonDto>> getSalons() {
        List<Salon> salon=salonService.getAllSalons();
        List<SalonDto> salonDos=salon.stream().map(SalonMapper::toSalonDto).toList();
        return ResponseEntity.ok(salonDos);
    }

    @GetMapping("/{salonId}")
    public ResponseEntity<SalonDto> getSalonById(@PathVariable Long salonId) {
        Salon salon=salonService.getSalonById(salonId);
        return ResponseEntity.ok(SalonMapper.toSalonDto(salon));
    }


    @GetMapping("/search")
    public ResponseEntity<List<SalonDto>> searchSalons(@RequestParam("city") String city) {
        List<Salon> salon=salonService.searchSalonByCity(city);
        List<SalonDto> salonDos=salon.stream().map(SalonMapper::toSalonDto).toList();
        return ResponseEntity.ok(salonDos);
    }


    @GetMapping("/owner")
    public ResponseEntity<SalonDto> getSalonByOwnerId() {
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        Salon salon=salonService.getSalonByOwnerId(userDto.getId());
        return ResponseEntity.ok(SalonMapper.toSalonDto(salon));
    }



}
