package org.nrr.category_service.repository;

import org.nrr.category_service.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    Set<Category> findBySalonId(Long salonId);
    Category findByIdAndSalonId(Long id, Long salonId);

}
