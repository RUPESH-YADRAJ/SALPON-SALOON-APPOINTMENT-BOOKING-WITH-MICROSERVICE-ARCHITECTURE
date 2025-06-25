package org.nrr.category_service.service;


import org.nrr.category_service.dto.SalonDto;
import org.nrr.category_service.model.Category;

import java.util.Set;

public interface CategoryService {
    Category save(Category category, SalonDto salonDto);
    Set<Category> getAllCategoriesBySalonId(Long salonId);

    Category getCategoryById(Long id) throws Exception;

    void deleteCategoryById(Long id,Long salonId) throws Exception;

    Category findByIdAndSalonId(Long id, Long salonId) throws Exception;

}
