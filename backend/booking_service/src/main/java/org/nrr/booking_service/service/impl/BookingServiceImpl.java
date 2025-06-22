package org.nrr.booking_service.service.impl;

import org.nrr.booking_service.dto.BookingRequest;
import org.nrr.booking_service.dto.SalonDto;
import org.nrr.booking_service.dto.ServiceDto;
import org.nrr.booking_service.dto.UserDto;
import org.nrr.booking_service.model.Booking;
import org.nrr.booking_service.model.BookingStatus;
import org.nrr.booking_service.model.SalonReport;
import org.nrr.booking_service.repository.BookingRepository;
import org.nrr.booking_service.service.BookingService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Booking createBooking(BookingRequest bookingRequest,
                                 UserDto userDTO,
                                 SalonDto salonDTO,
                                 Set<ServiceDto> serviceDtoSet) throws Exception {
        int totalDuration= serviceDtoSet.stream()
                .mapToInt(ServiceDto::getDuration)
                .sum();
        LocalDateTime bookingStartTime=bookingRequest.getStartTime();
        LocalDateTime bookingEndTime=bookingStartTime.plusMinutes(totalDuration);

        Boolean isAvailable=isTimeSlotAvailable(salonDTO,bookingStartTime,bookingEndTime);
        int totalPrice= serviceDtoSet.stream()
                .mapToInt(ServiceDto::getPrice)
                .sum();
        Set<Long> idList= serviceDtoSet.stream()
                .map(ServiceDto::getId)
                .collect(Collectors.toSet());

        Booking newBooking=Booking.builder()
                .customerId(userDTO.getId())
                .salonId(salonDTO.getId())
                .serviceId(idList)
                .bookingStatus(BookingStatus.PENDING)
                .startTime(bookingStartTime)
                .endTime(bookingEndTime)
                .totalPrice(totalPrice)
                .build();
        return bookingRepository.save(newBooking);
    }

    public Boolean isTimeSlotAvailable(SalonDto salonDTO,
                                       LocalDateTime bookingStartTime,
                                       LocalDateTime bookingEndTime) throws Exception {

        List<Booking> existingBookings=getBookingBySalon(salonDTO.getId());

        LocalDateTime salonOpenTime= salonDTO.getOpenTime().atDate(bookingStartTime.toLocalDate());
        LocalDateTime salonCloseTime= salonDTO.getCloseTime().atDate(bookingEndTime.toLocalDate());
        if(bookingStartTime.isBefore(salonOpenTime) || bookingEndTime.isAfter(salonCloseTime)) {
            throw new Exception("Booking time must be within salon's working hours");
        }

        for(Booking existingBooking: existingBookings){
            LocalDateTime existingBookingStartTime=existingBooking.getStartTime();
            LocalDateTime existingBookingEndTime=existingBooking.getEndTime();

            if(bookingStartTime.isBefore(existingBookingEndTime) && bookingEndTime.isAfter(existingBookingStartTime)) {
                throw new Exception("Slot not available, choose different time");
            }

            if(bookingStartTime.isEqual(existingBookingStartTime) || bookingEndTime.isEqual(existingBookingEndTime)) {
                throw new Exception("Slot not available, choose different time");
            }

        }
        return true;

    }

    @Override
    public List<Booking> getBookingByCustomer(Long customerId) {

        return bookingRepository.findByCustomerId(customerId);
    }

    @Override
    public List<Booking> getBookingBySalon(Long salonId) {
        return bookingRepository.findBySalonId(salonId);
    }

    @Override
    public Booking getBookingById(Long id) throws Exception {
        Booking booking=bookingRepository.findById(id).orElse(null);
        if(booking==null) {
            throw new Exception("Booking not found!");
        }
        return booking;
    }

    @Override
    public Booking updateBooking(Long bookingId, BookingStatus status) throws Exception {
        Booking booking=getBookingById(bookingId);
        booking.setBookingStatus(status);

        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getBookingByDate(LocalDate date, Long salonId) {
        List<Booking> allBooking=getBookingBySalon(salonId);
        if(date==null){
            return allBooking;
        }
        return allBooking.stream()
                .filter(booking->isSameDate(booking.getStartTime(),date)|| isSameDate(booking.getEndTime(),date))
                .collect(Collectors.toList());
    }

    private boolean isSameDate(LocalDateTime dateTime, LocalDate date) {
        return dateTime.toLocalDate().isEqual(date);
    }

    @Override
    public SalonReport getSalonReport(Long salonId) {
        List<Booking> bookings=getBookingBySalon(salonId);
        int totalEarning= bookings.stream()
                .mapToInt(Booking::getTotalPrice)
                .sum();
        Integer totalBooking=bookings.size();

        List<Booking> cancelledBooking= bookings.stream()
                .filter(booking->booking.getBookingStatus().equals(BookingStatus.CANCELLED))
                .toList();
        Double totalRefund=cancelledBooking.stream()
                .mapToDouble(Booking::getTotalPrice)
                .sum();
        return  SalonReport.builder()
                .salonId(salonId)
                .cancelBooking(cancelledBooking.size())
                .totalBooking(totalBooking)
                .totalEarning(totalEarning)
                .totalRefund(totalRefund)
                .build();
    }
}
