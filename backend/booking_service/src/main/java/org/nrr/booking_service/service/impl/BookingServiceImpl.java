package org.nrr.booking_service.service.impl;

import org.nrr.booking_service.dto.*;
import org.nrr.booking_service.model.Booking;
import org.nrr.booking_service.domain.BookingStatus;
import org.nrr.booking_service.model.PaymentOrder;
import org.nrr.booking_service.model.SalonReport;
import org.nrr.booking_service.repository.BookingRepository;
import org.nrr.booking_service.service.BookingService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
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

        LocalDateTime startTime = bookingRequest.getStartTime();
        if (startTime.isBefore(LocalDateTime.now())) {
            throw new Exception("Cannot book an appointment in the past.");
        }

        int duration = serviceDtoSet.stream().mapToInt(ServiceDto::getDuration).sum();
        LocalDateTime endTime = startTime.plusMinutes(duration);

        validateTimeWithinSalonHours(salonDTO, startTime, endTime);

        Set<Long> bookedSeatIds = bookingRepository.findBookedSeatIds(salonDTO.getId(), startTime, endTime);
        Set<Long> requestedSeatIds = bookingRequest.getSeatIds();

        for (Long seatId : requestedSeatIds) {
            if (bookedSeatIds.contains(seatId)) {
                throw new Exception("Seat " + seatId + " is already booked at this time");
            }
        }

        int perSeatPrice = serviceDtoSet.stream().mapToInt(ServiceDto::getPrice).sum();
        int totalPrice = perSeatPrice * requestedSeatIds.size();
        Set<Long> serviceIds = serviceDtoSet.stream().map(ServiceDto::getId).collect(Collectors.toSet());

        Booking booking = Booking.builder()
                .customerId(userDTO.getId())
                .salonId(salonDTO.getId())
                .serviceId(serviceIds)
                .bookingStatus(BookingStatus.PENDING)
                .startTime(startTime)
                .endTime(endTime)
                .totalPrice(totalPrice)
                .seatIds(requestedSeatIds)
                .build();

        return bookingRepository.save(booking);
    }

    private void validateTimeWithinSalonHours(SalonDto salonDTO,
                                              LocalDateTime start,
                                              LocalDateTime end) throws Exception {
        LocalTime open = salonDTO.getStartTime();
        LocalTime close = salonDTO.getCloseTime();

        if (start.toLocalTime().isBefore(open) || end.toLocalTime().isAfter(close)) {
            throw new Exception("Booking must be within salon working hours.");
        }
    }


    @Override
    public AvailableSeatsResponse getAvailableSeats(SalonDto salonDto,
                                                    LocalDateTime startTime,
                                                    Set<ServiceDto> services,
                                                    List<SeatDto> allSeats) {

        int totalDuration = services.stream().mapToInt(ServiceDto::getDuration).sum();
        LocalDateTime endTime = startTime.plusMinutes(totalDuration);

        // 1. Fetch bookings overlapping with the requested time
        List<Booking> overlappingBookings = bookingRepository.findBySalonIdAndTimeRange(
                salonDto.getId(), startTime, endTime
        );

        // 2. Collect booked seat info
        List<BookedSeatInfo> bookedSeatInfos = new ArrayList<>();
        Set<Long> bookedSeatIds = new HashSet<>();

        for (Booking booking : overlappingBookings) {
            for (Long seatId : booking.getSeatIds()) {
                bookedSeatIds.add(seatId);
                bookedSeatInfos.add(new BookedSeatInfo(seatId, booking.getStartTime(), booking.getEndTime()));
            }
        }

        // 3. All available seats
        Set<Long> allSeatIds = allSeats.stream().map(SeatDto::getId).collect(Collectors.toSet());
        Set<Long> availableSeatIds = allSeatIds.stream()
                .filter(id -> !bookedSeatIds.contains(id))
                .collect(Collectors.toSet());

        // 4. Return response
        AvailableSeatsResponse response = new AvailableSeatsResponse();
        response.setAvailableSeatIds(availableSeatIds);
        response.setBookedSeats(bookedSeatInfos);
        return response;

    }


    public Boolean isTimeSlotAvailable(SalonDto salonDTO,
                                       LocalDateTime bookingStartTime,
                                       LocalDateTime bookingEndTime) throws Exception {

        List<Booking> existingBookings=getBookingBySalon(salonDTO.getId());

        LocalDateTime salonOpenTime= salonDTO.getStartTime().atDate(bookingStartTime.toLocalDate());
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

    @Override
    public Booking bookingSuccess(PaymentOrder paymentOrder) throws Exception {
        Booking existingBooking=getBookingById(paymentOrder.getBookingId());
        existingBooking.setBookingStatus(BookingStatus.CONFIRMED);
        return bookingRepository.save(existingBooking);
    }
}
