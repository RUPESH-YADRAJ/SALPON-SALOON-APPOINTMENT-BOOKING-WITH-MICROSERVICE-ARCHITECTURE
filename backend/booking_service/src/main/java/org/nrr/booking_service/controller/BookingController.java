package org.nrr.booking_service.controller;

import org.nrr.booking_service.dto.*;
import org.nrr.booking_service.mapper.BookingMapper;
import org.nrr.booking_service.model.Booking;
import org.nrr.booking_service.model.BookingStatus;
import org.nrr.booking_service.model.SalonReport;
import org.nrr.booking_service.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping()
    public ResponseEntity<Booking> createBooking(@RequestParam Long salonId, @RequestBody BookingRequest bookingRequests) throws Exception {
        UserDto userDto= UserDto.builder()
                .id(1L)
                .build();
        SalonDto salonDTO= SalonDto.builder()
                .id(1L)
                .openTime(LocalTime.now())
                .closeTime(LocalTime.now().plusHours(12))
                .build();

        Set<ServiceDto> serviceDtoSet = new HashSet<>();
        ServiceDto serviceDTO= ServiceDto.builder()
                .id(1L)
                .price(399)
                .duration(45)
                .name("Hair cut for men")
                .build();
        serviceDtoSet.add(serviceDTO);


        return ResponseEntity.ok(  bookingService.createBooking(bookingRequests,userDto,salonDTO, serviceDtoSet));
    }

    @GetMapping("/customer")
    public ResponseEntity<Set<BookingDto>> getBookingByCustomer(){
        UserDto userDto= UserDto.builder()
                .id(1L)
                .build();

        List<Booking> bookings=bookingService.getBookingByCustomer(1L);

        return ResponseEntity.ok(getBookingDTOs(bookings));
    }

    private Set<BookingDto> getBookingDTOs(List<Booking> bookings){
        return bookings.stream()
                .map(BookingMapper::toBookingDto)
                .collect(Collectors.toSet());
    }

    @GetMapping("/salon")
    public ResponseEntity<Set<BookingDto>> getBookingBySalon(){
        List<Booking> bookings=bookingService.getBookingBySalon(1L);

        return ResponseEntity.ok(getBookingDTOs(bookings));
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingDto> getBookingById(@PathVariable Long bookingId) throws Exception {
        Booking booking=bookingService.getBookingById(bookingId);

        return ResponseEntity.ok(BookingMapper.toBookingDto(booking));
    }

    @PutMapping("/{bookingId}/status")
    public ResponseEntity<BookingDto> updateBookingStatus(@PathVariable Long bookingId,
                                                          @RequestParam BookingStatus status) throws Exception {
        Booking booking=bookingService.updateBooking(bookingId,status);

        return ResponseEntity.ok(BookingMapper.toBookingDto(booking));
    }


    @GetMapping("/slot/salon/{salonId}/date/{date}")
    public ResponseEntity<List<BookingSlotDto>> getBookedSlot(@PathVariable Long salonId, @RequestParam LocalDate date) throws Exception {
        List<Booking> bookings=bookingService.getBookingByDate(date,salonId);
        List<BookingSlotDto> bookingSlotDtos =bookings.stream()
                .map(booking->{
                    return BookingSlotDto.builder()
                            .startTime(booking.getStartTime())
                            .endTime(booking.getEndTime())
                            .build();
                }).toList();

        return ResponseEntity.ok(bookingSlotDtos);
    }


    @GetMapping("/report")
    public ResponseEntity<SalonReport> getSalonReport() throws Exception {
        SalonReport report=bookingService.getSalonReport(1L);


        return ResponseEntity.ok(report);
    }

}
