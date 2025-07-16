package org.nrr.salon_service.repository;

import org.nrr.salon_service.models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat,Long> {
    List<Seat> findSeatsBySalonId(Long salonId);
}
