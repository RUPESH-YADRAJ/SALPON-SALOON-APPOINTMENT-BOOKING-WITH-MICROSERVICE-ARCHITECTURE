package org.nrr.booking_service.service;

import org.nrr.booking_service.dto.BookingRequest;
import org.nrr.booking_service.dto.SalonDto;
import org.nrr.booking_service.dto.ServiceDto;
import org.nrr.booking_service.dto.UserDto;
import org.nrr.booking_service.model.Booking;
import org.nrr.booking_service.domain.BookingStatus;
import org.nrr.booking_service.model.PaymentOrder;
import org.nrr.booking_service.model.SalonReport;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface BookingService {

    Booking createBooking(BookingRequest bookingRequest,
                          UserDto userDTO,
                          SalonDto salonDTO,
                          Set<ServiceDto> serviceDtoSet) throws Exception;

    List<Booking> getBookingByCustomer(Long customerId);

    List<Booking> getBookingBySalon(Long salonId);

    Booking getBookingById(Long id) throws Exception;

    Booking updateBooking(Long bookingId, BookingStatus status) throws Exception;

    List<Booking> getBookingByDate(LocalDate date,Long salonId);

    SalonReport getSalonReport(Long salonId);

    Booking bookingSuccess(PaymentOrder paymentOrder) throws Exception;


}
