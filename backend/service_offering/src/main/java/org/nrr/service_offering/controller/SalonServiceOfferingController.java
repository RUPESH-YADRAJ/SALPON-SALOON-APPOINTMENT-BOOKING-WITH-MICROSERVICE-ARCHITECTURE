package org.nrr.service_offering.controller;

import org.nrr.service_offering.dto.CategoryDto;
import org.nrr.service_offering.dto.SalonDto;
import org.nrr.service_offering.dto.ServiceDto;
import org.nrr.service_offering.model.ServiceOffering;
import org.nrr.service_offering.service.ServiceOfferingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/service-offering/salon-owner")
public class SalonServiceOfferingController {

    private final ServiceOfferingService serviceOfferingService;

    public SalonServiceOfferingController(ServiceOfferingService serviceOfferingService) {
        this.serviceOfferingService = serviceOfferingService;
    }

    @PostMapping
    public ResponseEntity<ServiceOffering> createService(@RequestBody ServiceDto serviceDto) {

        SalonDto salonDto=SalonDto.builder()
                .id(1L)
                .build();
        CategoryDto categoryDto=CategoryDto.builder()
                .id(serviceDto.getCategory())
                .build();
        ServiceOffering serviceOffering =serviceOfferingService.create(salonDto,serviceDto,categoryDto);
        return ResponseEntity.ok(serviceOffering);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceOffering> updateService(@PathVariable Long id, @RequestBody ServiceOffering serviceOffering) throws Exception {

        ServiceOffering updateService =serviceOfferingService.updateService(id,serviceOffering);
        return ResponseEntity.ok(updateService);
    }
}
