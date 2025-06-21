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
        UserDTO userDto= UserDTO.builder()
                .id(1L)
                .build();
        SalonDTO salonDTO=SalonDTO.builder()
                .id(1L)
                .openTime(LocalTime.now())
                .closeTime(LocalTime.now().plusHours(12))
                .build();

        Set<ServiceDTO> serviceDTOSet= new HashSet<>();
        ServiceDTO serviceDTO=ServiceDTO.builder()
                .id(1L)
                .price(399)
                .duration(45)
                .name("Hair cut for men")
                .build();
        serviceDTOSet.add(serviceDTO);


        return ResponseEntity.ok(  bookingService.createBooking(bookingRequests,userDto,salonDTO,serviceDTOSet));
    }

    @GetMapping("/customer")
    public ResponseEntity<Set<BookingDTO>> getBookingByCustomer(){
        UserDTO userDto= UserDTO.builder()
                .id(1L)
                .build();

        List<Booking> bookings=bookingService.getBookingByCustomer(1L);

        return ResponseEntity.ok(getBookingDTOs(bookings));
    }

    private Set<BookingDTO> getBookingDTOs(List<Booking> bookings){
        return bookings.stream()
                .map(BookingMapper::toBookingDto)
                .collect(Collectors.toSet());
    }

    @GetMapping("/salon")
    public ResponseEntity<Set<BookingDTO>> getBookingBySalon(){
        List<Booking> bookings=bookingService.getBookingBySalon(1L);

        return ResponseEntity.ok(getBookingDTOs(bookings));
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingDTO> getBookingById(@PathVariable Long bookingId) throws Exception {
        Booking booking=bookingService.getBookingById(bookingId);

        return ResponseEntity.ok(BookingMapper.toBookingDto(booking));
    }

    @PutMapping("/{bookingId}/status")
    public ResponseEntity<BookingDTO> updateBookingStatus(@PathVariable Long bookingId,
                                                          @RequestParam BookingStatus status) throws Exception {
        Booking booking=bookingService.updateBooking(bookingId,status);

        return ResponseEntity.ok(BookingMapper.toBookingDto(booking));
    }


    @GetMapping("/slot/salon/{salonId}/date/{date}")
    public ResponseEntity<List<BookingSlotDTO>> getBookedSlot(@PathVariable Long salonId, @RequestParam LocalDate date) throws Exception {
        List<Booking> bookings=bookingService.getBookingByDate(date,salonId);
        List<BookingSlotDTO> bookingSlotDTOS=bookings.stream()
                .map(booking->{
                    return BookingSlotDTO.builder()
                            .startTime(booking.getStartTime())
                            .endTime(booking.getEndTime())
                            .build();
                }).toList();

        return ResponseEntity.ok(bookingSlotDTOS);
    }


    @GetMapping("/report")
    public ResponseEntity<SalonReport> getSalonReport() throws Exception {
        SalonReport report=bookingService.getSalonReport(1L);


        return ResponseEntity.ok(report);
    }

}
