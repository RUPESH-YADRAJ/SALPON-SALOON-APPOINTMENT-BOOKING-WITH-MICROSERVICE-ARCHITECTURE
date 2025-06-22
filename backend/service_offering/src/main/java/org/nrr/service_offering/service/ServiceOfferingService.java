package org.nrr.service_offering.service;


import org.nrr.service_offering.dto.CategoryDto;
import org.nrr.service_offering.dto.SalonDto;
import org.nrr.service_offering.dto.ServiceDto;
import org.nrr.service_offering.model.ServiceOffering;

import java.util.List;
import java.util.Set;

public interface ServiceOfferingService {
    ServiceOffering create(SalonDto salonDTO, ServiceDto serviceDTO, CategoryDto categoryDTO);
    ServiceOffering updateService(Long serviceId, ServiceOffering serviceOffering) throws Exception;
    Set<ServiceOffering> getAllServiceBySalonId(Long salonId,Long categoryId);

    Set<ServiceOffering> getServiceByIds(Set<Long> serviceIds);

    ServiceOffering getServiceById(Long serviceId) throws Exception;
}
