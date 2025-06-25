package org.nrr.service_offering.controller;

import org.nrr.service_offering.dto.CategoryDto;
import org.nrr.service_offering.dto.SalonDto;
import org.nrr.service_offering.dto.ServiceDto;
import org.nrr.service_offering.model.ServiceOffering;
import org.nrr.service_offering.service.ServiceOfferingService;
import org.nrr.service_offering.service.client.CategoryFeignClient;
import org.nrr.service_offering.service.client.SalonFeignClient;
import org.nrr.service_offering.service.client.UserFeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/service-offering/salon-owner")
public class SalonServiceOfferingController {

    private final ServiceOfferingService serviceOfferingService;
    private final CategoryFeignClient categoryFeignClient;
    private final SalonFeignClient salonFeignClient;


    public SalonServiceOfferingController(ServiceOfferingService serviceOfferingService, CategoryFeignClient categoryFeignClient, SalonFeignClient salonFeignClient) {
        this.serviceOfferingService = serviceOfferingService;
        this.categoryFeignClient = categoryFeignClient;
        this.salonFeignClient = salonFeignClient;

    }

    @PostMapping
    public ResponseEntity<ServiceOffering> createService(@RequestBody ServiceDto serviceDto,@RequestHeader("Authorization") String jwt) throws Exception {

        SalonDto salonDto=salonFeignClient.getSalonByOwnerId(jwt).getBody();
        CategoryDto categoryDto=categoryFeignClient.getCategoryByIdAndSalon(serviceDto.getCategory(),salonDto.getId()).getBody();
        ServiceOffering serviceOffering =serviceOfferingService.create(salonDto,serviceDto,categoryDto);
        return ResponseEntity.ok(serviceOffering);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceOffering> updateService(@PathVariable Long id, @RequestBody ServiceOffering serviceOffering) throws Exception {

        ServiceOffering updateService =serviceOfferingService.updateService(id,serviceOffering);
        return ResponseEntity.ok(updateService);
    }
}
