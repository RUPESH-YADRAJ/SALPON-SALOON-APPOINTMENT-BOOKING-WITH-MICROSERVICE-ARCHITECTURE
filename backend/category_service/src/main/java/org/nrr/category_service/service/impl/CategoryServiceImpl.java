package org.nrr.category_service.service.impl;

import org.nrr.category_service.dto.SalonDto;
import org.nrr.category_service.model.Category;
import org.nrr.category_service.repository.CategoryRepository;
import org.nrr.category_service.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category save(Category category, SalonDto salonDto) {

        Category newCategory = Category.builder()
                .name(category.getName())
                .salonId(salonDto.getId())
                .images(category.getImages())
                .build();

        return categoryRepository.save(newCategory);
    }

    @Override
    public Set<Category> getAllCategoriesBySalonId(Long salonId) {
        System.out.println(categoryRepository.findBySalonId(salonId));
        return categoryRepository.findBySalonId(salonId);

    }

    @Override
    public Category getCategoryById(Long id) throws Exception {
        Category category = categoryRepository.findById(id).orElse(null);
        if (category == null) {
            throw new Exception("category not exist with id "+ id);
        }
        return category;
    }

    @Override
    public void deleteCategoryById(Long id,Long salonId) throws Exception {
        Category category = getCategoryById(id);
        if (!Objects.equals(category.getSalonId(), salonId)) {
            throw new Exception("you don't have permission to delete this category ");
        }
        categoryRepository.delete(category);
    }

    @Override
    public Category findByIdAndSalonId(Long id, Long salonId) throws Exception {
        Category category= categoryRepository.findByIdAndSalonId(id,salonId);
        if (category == null) {
            throw new Exception("Category not found");
        }
        return category;
    }
}
