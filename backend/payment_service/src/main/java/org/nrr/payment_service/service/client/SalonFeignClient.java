package org.nrr.payment_service.service.client;


import org.nrr.payment_service.payload.dto.SalonDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient("SALON-SERVICE")
public interface SalonFeignClient {
    @GetMapping("/api/salons/owner")
    public ResponseEntity<SalonDto> getSalonByOwnerId(@RequestHeader("Authorization") String jwt) throws Exception;

    @GetMapping("api/salons/{salonId}")
    public ResponseEntity<SalonDto> getSalonById(@PathVariable Long salonId);
}
