package org.nrr.payment_service.service.client;


import org.nrr.payment_service.payload.dto.ServiceDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

@FeignClient("SERVICE-OFFERING")
public interface ServiceOfferingFeignClient {
    @GetMapping("/api/service-offering/list/{ids}")
    public ResponseEntity<Set<ServiceDto>> getServicesByIds(@PathVariable Set<Long> ids) throws Exception;
}
