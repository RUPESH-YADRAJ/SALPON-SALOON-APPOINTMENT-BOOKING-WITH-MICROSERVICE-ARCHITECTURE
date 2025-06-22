package org.nrr.service_offering.service.impl;

import org.nrr.service_offering.dto.CategoryDto;
import org.nrr.service_offering.dto.SalonDto;
import org.nrr.service_offering.dto.ServiceDto;
import org.nrr.service_offering.model.ServiceOffering;
import org.nrr.service_offering.repository.ServiceOfferingRepository;
import org.nrr.service_offering.service.ServiceOfferingService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ServiceOfferingServiceImpl implements ServiceOfferingService {

    private final ServiceOfferingRepository serviceOfferingRepository;

    public ServiceOfferingServiceImpl(ServiceOfferingRepository serviceOfferingRepository) {
        this.serviceOfferingRepository = serviceOfferingRepository;
    }

    @Override
    public ServiceOffering create(SalonDto salonDTO, ServiceDto serviceDTO, CategoryDto categoryDTO) {
        ServiceOffering serviceOffering = ServiceOffering.builder()
                .image(serviceDTO.getImage())
                .salonId(salonDTO.getId())
                .name(serviceDTO.getName())
                .description(serviceDTO.getDescription())
                .categoryId(categoryDTO.getId())
                .price(serviceDTO.getPrice())
                .duration(serviceDTO.getDuration())
                .build();
        return serviceOfferingRepository.save(serviceOffering);
    }

    @Override
    public ServiceOffering updateService(Long serviceId, ServiceOffering serviceOffering) throws Exception {
        ServiceOffering existingServiceOffering=serviceOfferingRepository.findById(serviceId).orElse(null);
        if (existingServiceOffering==null) {
            throw new Exception("service not exist with id  "+ serviceId);
        }
        ServiceOffering updated = ServiceOffering.builder()
                .image(serviceOffering.getImage())
                .name(serviceOffering.getName())
                .description(serviceOffering.getDescription())
                .price(serviceOffering.getPrice())
                .duration(serviceOffering.getDuration())
                .build();
        return serviceOfferingRepository.save(updated);
    }

    @Override
    public Set<ServiceOffering> getAllServiceBySalonId(Long salonId, Long categoryId) {

        Set<ServiceOffering> serviceOfferings= serviceOfferingRepository.findBySalonId(salonId);
        if(categoryId!=null) {
            serviceOfferings=serviceOfferings.stream()
                    .filter((service)->service.getCategoryId()!=null && service.getCategoryId().equals(categoryId))
                    .collect(Collectors.toSet());
        }
        return serviceOfferings;
    }

    @Override
    public Set<ServiceOffering> getServiceByIds(Set<Long> serviceIds) {

         List<ServiceOffering> serviceOfferings=serviceOfferingRepository.findAllById(serviceIds);
        return new HashSet<>(serviceOfferings);
    }

    @Override
    public ServiceOffering getServiceById(Long serviceId) throws Exception {

        ServiceOffering serviceOffering=serviceOfferingRepository.findById(serviceId).orElse(null);
        if (serviceOffering==null) {
            throw new Exception("service not exist with id  "+ serviceId);
        }
        return serviceOffering;
    }
}
