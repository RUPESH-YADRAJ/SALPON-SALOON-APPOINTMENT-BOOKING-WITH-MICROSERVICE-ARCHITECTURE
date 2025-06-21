package org.nrr.booking_service.service;

import org.nrr.booking_service.dto.BookingRequest;
import org.nrr.booking_service.dto.SalonDTO;
import org.nrr.booking_service.dto.ServiceDTO;
import org.nrr.booking_service.dto.UserDTO;
import org.nrr.booking_service.model.Booking;
import org.nrr.booking_service.model.BookingStatus;
import org.nrr.booking_service.model.SalonReport;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface BookingService {

    Booking createBooking(BookingRequest bookingRequest,
                          UserDTO userDTO,
                          SalonDTO  salonDTO,
                          Set<ServiceDTO> serviceDTOSet) throws Exception;

    List<Booking> getBookingByCustomer(Long customerId);

    List<Booking> getBookingBySalon(Long salonId);

    Booking getBookingById(Long id) throws Exception;

    Booking updateBooking(Long bookingId, BookingStatus status) throws Exception;

    List<Booking> getBookingByDate(LocalDate date,Long salonId);

    SalonReport getSalonReport(Long salonId);


}
