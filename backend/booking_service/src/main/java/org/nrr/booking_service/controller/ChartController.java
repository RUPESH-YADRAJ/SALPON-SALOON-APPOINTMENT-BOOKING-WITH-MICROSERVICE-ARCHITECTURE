package org.nrr.booking_service.controller;

import lombok.RequiredArgsConstructor;
import org.nrr.booking_service.dto.SalonDto;
import org.nrr.booking_service.model.Booking;
import org.nrr.booking_service.service.BookingService;
import org.nrr.booking_service.service.client.SalonFeignClient;
import org.nrr.booking_service.service.impl.BookingChartServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bookings/chart")

public class ChartController {

    private final BookingChartServiceImpl bookingChartService;
    private final BookingService bookingService;
    private final SalonFeignClient salonService;

    @GetMapping("/earnings")
    public ResponseEntity<List<Map<String, Object>>> getEarningsChartData(
            @RequestHeader("Authorization") String jwt) throws Exception {
        SalonDto salon = salonService.getSalonByOwnerId(jwt).getBody();
        List<Booking> bookings=bookingService.getBookingBySalon(salon.getId());
        List<Map<String, Object>> chartData = bookingChartService
                .generateEarningsChartData(bookings);

        return ResponseEntity.ok(chartData);

    }

    @GetMapping("/bookings")
    public ResponseEntity<List<Map<String, Object>>> getBookingsChartData(
            @RequestHeader("Authorization") String jwt) throws Exception {

        SalonDto salon = salonService.getSalonByOwnerId(jwt).getBody();
        List<Booking> bookings=bookingService.getBookingBySalon(salon.getId());
        List<Map<String, Object>> chartData = bookingChartService.generateBookingCountChartData(bookings);

        return ResponseEntity.ok(chartData);

    }



}
