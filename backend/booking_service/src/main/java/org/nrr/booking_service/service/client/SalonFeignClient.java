package org.nrr.booking_service.service.client;


import org.nrr.booking_service.dto.SalonDto;
import org.nrr.booking_service.dto.SeatDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient("SALON-SERVICE")
public interface SalonFeignClient {
    @GetMapping("/api/salons/owner")
    public ResponseEntity<SalonDto> getSalonByOwnerId(@RequestHeader("Authorization") String jwt) throws Exception;

    @GetMapping("api/salons/{salonId}")
    public ResponseEntity<SalonDto> getSalonById(@PathVariable Long salonId);

    @GetMapping("/api/salons/{salonId}/seats")
    public ResponseEntity<List<SeatDto>> getAllSeats(@RequestHeader("Authorization") String jwt, @PathVariable Long salonId) throws Exception;
}
