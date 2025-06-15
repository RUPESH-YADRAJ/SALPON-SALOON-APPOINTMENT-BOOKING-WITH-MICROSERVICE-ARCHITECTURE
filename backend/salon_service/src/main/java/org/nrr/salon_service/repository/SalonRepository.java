package org.nrr.salon_service.repository;

import org.nrr.salon_service.models.Salon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalonRepository extends JpaRepository<Salon,Long> {
    Salon findSalonByOwnerId(Long id);

    @Query(
            "select s from Salon s where"+
                    "(lower(s.city) like lower(concat('%',:keyword, '%') ) OR "+
                    "lower(s.name) like lower(concat('%',:keyword, '%') ) OR  " +
                    "lower(s.address) like lower(concat('%',:keyword, '%') ) )"
    )
    List<Salon> searchSalon(@Param("keyword") String keyword);

}
