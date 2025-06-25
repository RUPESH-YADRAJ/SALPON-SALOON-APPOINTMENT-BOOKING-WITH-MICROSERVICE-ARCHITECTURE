package org.nrr.payment_service.service.client;


import org.nrr.payment_service.payload.dto.CategoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("CATEGORY-SERVICE")
public interface CategoryFeignClient {
//    @GetMapping("/api/categories/{id}")
//    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id)throws  Exception;

    @GetMapping("/api/categories/salon-owner/salon/{salonId}/category/{id}")
    public ResponseEntity<CategoryDto> getCategoryByIdAndSalon(@PathVariable Long id, @PathVariable Long salonId) throws Exception;
}