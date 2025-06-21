package org.nrr.category_service.controller;


import org.nrr.category_service.dto.SalonDto;
import org.nrr.category_service.model.Category;
import org.nrr.category_service.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories/salon-owner")
public class SalonCategoryController {
private final CategoryService categoryService;

    public SalonCategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping()
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        SalonDto salonDto=SalonDto.builder()
                .id(1L)
                .build();
        return ResponseEntity.ok(categoryService.save(category,salonDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) throws Exception {
        SalonDto salonDto=SalonDto.builder()
                .id(1L)
                .build();
        categoryService.deleteCategoryById(id,salonDto.getId());
        return ResponseEntity.ok("Category deleted successfully");
    }
}
