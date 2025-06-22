package org.nrr.booking_service.mapper;

import org.nrr.booking_service.dto.BookingDto;
import org.nrr.booking_service.model.Booking;

public class BookingMapper {
    public static BookingDto toBookingDto(Booking booking) {
        return BookingDto.builder()
                .id(booking.getId())
                .bookingStatus(booking.getBookingStatus())
                .customerId(booking.getCustomerId())
                .salonId(booking.getSalonId())
                .endTime(booking.getEndTime())
                .startTime(booking.getStartTime())
                .serviceId(booking.getServiceId())
                .build();

    }
}
