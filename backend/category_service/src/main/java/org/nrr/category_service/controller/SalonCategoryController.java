package org.nrr.category_service.controller;


import org.nrr.category_service.dto.SalonDto;
import org.nrr.category_service.model.Category;
import org.nrr.category_service.service.CategoryService;
import org.nrr.category_service.service.client.SalonFeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories/salon-owner")
public class SalonCategoryController {
private final CategoryService categoryService;
private final SalonFeignClient salonFeignClient;


    public SalonCategoryController(CategoryService categoryService, SalonFeignClient salonFeignClient) {
        this.categoryService = categoryService;
        this.salonFeignClient = salonFeignClient;
    }

    @PostMapping()
    public ResponseEntity<Category> createCategory(@RequestBody Category category,@RequestHeader("Authorization") String jwt) throws Exception {

        SalonDto salonDto=salonFeignClient.getSalonByOwnerId(jwt).getBody();
        return ResponseEntity.ok(categoryService.save(category,salonDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id,@RequestHeader("Authorization") String jwt) throws Exception {
        SalonDto salonDto=salonFeignClient.getSalonByOwnerId(jwt).getBody();
        if (salonDto==null){
            return ResponseEntity.ok("Salon not found");
        }
        categoryService.deleteCategoryById(id,salonDto.getId());
        return ResponseEntity.ok("Category deleted successfully");
    }

    @GetMapping("/salon/{salonId}/category/{id}")
    public ResponseEntity<Category> getCategoryByIdAndSalon(@PathVariable Long id,@PathVariable Long salonId )throws  Exception {

        Category category=categoryService.findByIdAndSalonId(id,salonId);
        return ResponseEntity.ok(category);
    }
}
