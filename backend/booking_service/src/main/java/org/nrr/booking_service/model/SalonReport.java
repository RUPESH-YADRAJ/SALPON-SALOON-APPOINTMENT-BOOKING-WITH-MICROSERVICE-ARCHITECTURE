package org.nrr.booking_service.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SalonReport {
    private Long salonId;
    private String salonName;
    private int totalEarning;
    private Integer totalBooking;
    private Integer cancelBooking;
    private Double totalRefund;
}
