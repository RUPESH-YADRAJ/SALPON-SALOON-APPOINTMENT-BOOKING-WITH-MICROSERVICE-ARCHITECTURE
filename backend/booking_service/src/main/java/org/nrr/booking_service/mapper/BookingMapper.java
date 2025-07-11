package org.nrr.booking_service.mapper;

import org.nrr.booking_service.dto.BookingDto;
import org.nrr.booking_service.dto.SalonDto;
import org.nrr.booking_service.dto.ServiceDto;
import org.nrr.booking_service.dto.UserDto;
import org.nrr.booking_service.model.Booking;

import java.util.List;
import java.util.Set;

public class BookingMapper {
    public static BookingDto toBookingDto(Booking booking,
                                          Set<ServiceDto> services,
                                          UserDto user,
                                          SalonDto salon )
    {
        return BookingDto.builder()
                .id(booking.getId())
                .bookingStatus(booking.getBookingStatus())
                .customerId(booking.getCustomerId())
                .salonId(booking.getSalonId())
                .endTime(booking.getEndTime())
                .startTime(booking.getStartTime())
                .serviceId(booking.getServiceId())
                .totalPrice(booking.getTotalPrice())
                .salon(salon)
                .services(services)
                .user(user)
                .build();

    }
}
