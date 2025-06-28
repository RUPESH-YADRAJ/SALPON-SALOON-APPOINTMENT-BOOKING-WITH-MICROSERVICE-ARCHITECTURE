package org.nrr.booking_service.controller;

import jakarta.transaction.Transactional;
import org.nrr.booking_service.domain.PaymentMethod;
import org.nrr.booking_service.dto.*;
import org.nrr.booking_service.mapper.BookingMapper;
import org.nrr.booking_service.model.Booking;
import org.nrr.booking_service.domain.BookingStatus;
import org.nrr.booking_service.model.SalonReport;
import org.nrr.booking_service.service.BookingService;
import org.nrr.booking_service.service.client.PaymentFeignClient;
import org.nrr.booking_service.service.client.SalonFeignClient;
import org.nrr.booking_service.service.client.ServiceOfferingFeignClient;
import org.nrr.booking_service.service.client.UserFeignClient;
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
    private final UserFeignClient userFeignClient;
    private final SalonFeignClient salonFeignClient;
    private final ServiceOfferingFeignClient serviceOfferingFeignClient;
    private final PaymentFeignClient paymentFeignClient;

    public BookingController(BookingService bookingService, UserFeignClient userFeignClient, SalonFeignClient salonFeignClient, ServiceOfferingFeignClient serviceOfferingFeignClient, PaymentFeignClient paymentFeignClient) {
        this.bookingService = bookingService;
        this.userFeignClient = userFeignClient;
        this.salonFeignClient = salonFeignClient;
        this.serviceOfferingFeignClient = serviceOfferingFeignClient;
        this.paymentFeignClient = paymentFeignClient;
    }


    @Transactional
    @PostMapping()
    public ResponseEntity<PaymentLinkResponse> createBooking(@RequestParam Long salonId, @RequestParam PaymentMethod paymentMethod, @RequestBody BookingRequest bookingRequests, @RequestHeader("Authorization") String jwt) throws Exception {
        UserDto userDto= userFeignClient.getUserFromJwtToken(jwt).getBody();
        SalonDto salonDTO=salonFeignClient.getSalonById(salonId).getBody();

        Set<ServiceDto> serviceDto= serviceOfferingFeignClient.getServicesByIds(bookingRequests.getServiceIds()).getBody();

        Booking booking=bookingService.createBooking(bookingRequests,userDto,salonDTO, serviceDto);

        BookingDto bookingDto=BookingMapper.toBookingDto(booking);

        paymentFeignClient.createPaymentLink(bookingDto,paymentMethod,jwt);


        return ResponseEntity.ok(paymentFeignClient.createPaymentLink(bookingDto,paymentMethod,jwt).getBody());

    }

    @GetMapping("/customer")
    public ResponseEntity<Set<BookingDto>> getBookingByCustomer(@RequestHeader("Authorization") String jwt) throws Exception {
        UserDto userDto=userFeignClient.getUserFromJwtToken(jwt).getBody();
        if (userDto==null || userDto.getId()==null)
        {
             throw new Exception("User not found from jwt token");
        }
        List<Booking> bookings=bookingService.getBookingByCustomer(userDto.getId());

        return ResponseEntity.ok(getBookingDTOs(bookings));
    }

    private Set<BookingDto> getBookingDTOs(List<Booking> bookings){
        return bookings.stream()
                .map(BookingMapper::toBookingDto)
                .collect(Collectors.toSet());
    }

    @GetMapping("/salon")
    public ResponseEntity<Set<BookingDto>> getBookingBySalon(@RequestHeader("Authorization") String jwt) throws Exception {

        SalonDto salonDto=salonFeignClient.getSalonByOwnerId(jwt).getBody();
        if (salonDto==null || salonDto.getId()==null)
        {
            throw new Exception("Salon not found from jwt token");
        }
        List<Booking> bookings=bookingService.getBookingBySalon(salonDto.getId());

        return ResponseEntity.ok(getBookingDTOs(bookings));
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingDto> getBookingById(@PathVariable Long bookingId) throws Exception {
        Booking booking=bookingService.getBookingById(bookingId);
        System.out.println(booking);

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
    public ResponseEntity<SalonReport> getSalonReport(@RequestHeader("Authorization") String jwt) throws Exception {


        SalonDto salonDto=salonFeignClient.getSalonByOwnerId(jwt).getBody();
        if (salonDto==null || salonDto.getId()==null)
        {
            throw new Exception("Salon not found from jwt token");
        }
        SalonReport report=bookingService.getSalonReport(salonDto.getId());


        return ResponseEntity.ok(report);
    }

}
