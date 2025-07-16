package org.nrr.salon_service.service.impl;

import org.nrr.salon_service.mapper.SeatMapper;
import org.nrr.salon_service.models.Salon;
import org.nrr.salon_service.models.Seat;
import org.nrr.salon_service.payload.dto.SalonDto;
import org.nrr.salon_service.payload.dto.SeatDto;
import org.nrr.salon_service.payload.dto.UserDto;
import org.nrr.salon_service.repository.SalonRepository;
import org.nrr.salon_service.repository.SeatRepository;
import org.nrr.salon_service.service.SalonService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class SalonServiceImpl implements SalonService {

    private final SalonRepository salonRepository;
    private final SeatRepository seatRepository;

    public SalonServiceImpl(SalonRepository salonRepository, SeatRepository seatRepository) {
        this.salonRepository = salonRepository;
        this.seatRepository = seatRepository;
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
        salonRepository.save(salon);

        List<Seat> seats = IntStream.rangeClosed(1, salonDto.getSeatCounts())
                .mapToObj(i -> Seat.builder()
                        .seatNumber(String.valueOf(i))
                        .salon(salon)
                        .build())
                .toList();

        seatRepository.saveAll(seats);
        salon.setSeats(seats);
        return salon;


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



            int currentCount = existingSalon.getSeats() != null ? existingSalon.getSeats().size() : 0;
            int newSeatCount = salonDto.getSeatCounts();

            if (newSeatCount > currentCount) {
                List<Seat> seatsToAdd = IntStream.rangeClosed(currentCount + 1, newSeatCount)
                        .mapToObj(i -> Seat.builder()
                                .seatNumber(String.valueOf(i))
                                .salon(existingSalon)
                                .build())
                        .collect(Collectors.toList());

                seatRepository.saveAll(seatsToAdd);
                existingSalon.getSeats().addAll(seatsToAdd);

            } else if (newSeatCount < currentCount) {
                List<Seat> seatsToRemove = existingSalon.getSeats().stream()
                        .filter(seat -> Integer.parseInt(seat.getSeatNumber()) > newSeatCount)
                        .collect(Collectors.toList());

                seatRepository.deleteAll(seatsToRemove);
                existingSalon.getSeats().removeAll(seatsToRemove);
            }

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

    @Override
    public List<SeatDto> getAllSeatsOfSalon(Long id) {
        List<Seat> seat= seatRepository.findSeatsBySalonId(id);
        return seat.stream().map(SeatMapper::toSeatDto).toList();
    }

}
