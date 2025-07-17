package org.nrr.booking_service.repository;

import org.nrr.booking_service.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Long> {
    List<Booking> findByCustomerId(Long customerId);
    List<Booking> findBySalonId(Long salonId);

    @Query(value = "SELECT bs.seat_id FROM booking_seats bs " +
            "JOIN booking b ON bs.booking_id = b.id " +
            "WHERE b.salon_id = :salonId AND " +
            "(:startTime < b.end_time AND :endTime > b.start_time)", nativeQuery = true)
    Set<Long> findBookedSeatIds(@Param("salonId") Long salonId,
                                @Param("startTime") LocalDateTime startTime,
                                @Param("endTime") LocalDateTime endTime);


    @Query("SELECT b FROM Booking b WHERE b.salonId = :salonId AND " +
            "(:startTime < b.endTime AND :endTime > b.startTime)")
    List<Booking> findBySalonIdAndTimeRange(@Param("salonId") Long salonId,
                                            @Param("startTime") LocalDateTime startTime,
                                            @Param("endTime") LocalDateTime endTime);


}
