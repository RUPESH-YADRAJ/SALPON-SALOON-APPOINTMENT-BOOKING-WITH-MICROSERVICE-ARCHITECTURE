package org.nrr.service_offering.controller;

import org.nrr.service_offering.model.ServiceOffering;
import org.nrr.service_offering.service.ServiceOfferingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@RestController
@RequestMapping("/api/service-offering")
public class ServiceOfferingController {

    private final ServiceOfferingService serviceOfferingService;


    public ServiceOfferingController(ServiceOfferingService serviceOfferingService) {
        this.serviceOfferingService = serviceOfferingService;
    }

    @GetMapping("/salon/{salonId}")
    public ResponseEntity<Set<ServiceOffering>> getServicesBySalonId(@PathVariable Long salonId,
                                                                     @RequestParam(required = false) Long categoryId) {

        Set<ServiceOffering> serviceOfferings =serviceOfferingService.getAllServiceBySalonId(salonId, categoryId);
        return ResponseEntity.ok(serviceOfferings);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceOffering> getServicesById(@PathVariable Long id) throws Exception {

        ServiceOffering serviceOfferings =serviceOfferingService.getServiceById(id);
        return ResponseEntity.ok(serviceOfferings);

    }

    @GetMapping("/list/{ids}")
    public ResponseEntity<Set<ServiceOffering>> getServicesByIds(@PathVariable Set<Long> ids) throws Exception {

        Set<ServiceOffering> serviceOfferings =serviceOfferingService.getServiceByIds(ids);
        return ResponseEntity.ok(serviceOfferings);

    }


}
