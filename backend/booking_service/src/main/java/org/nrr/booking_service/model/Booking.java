package org.nrr.booking_service.model;

import jakarta.persistence.*;
import lombok.*;
import org.nrr.booking_service.domain.BookingStatus;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long salonId;

    @ElementCollection
    @CollectionTable(name = "booking_seats", joinColumns = @JoinColumn(name = "booking_id"))
    @Column(name = "seat_id")
    private Set<Long> seatIds;

    private Long customerId;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @ElementCollection
    private Set<Long> serviceId;

    private BookingStatus bookingStatus=BookingStatus.PENDING;

    private int totalPrice;

}
