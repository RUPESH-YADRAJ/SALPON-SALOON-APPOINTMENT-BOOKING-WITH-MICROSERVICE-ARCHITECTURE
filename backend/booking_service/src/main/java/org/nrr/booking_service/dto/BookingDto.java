package org.nrr.booking_service.dto;

import lombok.Builder;
import lombok.Data;
import org.nrr.booking_service.domain.BookingStatus;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
public class BookingDto {
    private Long id;

    private Long salonId;

    private Long customerId;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Set<Long> serviceId;

    private BookingStatus bookingStatus=BookingStatus.PENDING;

    private int totalPrice;
}
