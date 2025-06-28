package org.nrr.notification.controller;

import org.nrr.notification.mapper.NotificationMapper;
import org.nrr.notification.model.Notification;
import org.nrr.notification.payload.dto.BookingDto;
import org.nrr.notification.payload.dto.NotificationDto;
import org.nrr.notification.servcice.NotificationService;
import org.nrr.notification.servcice.client.BookingFeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/notifications/salon-owner")
public class SalonNotificationController {

    private final NotificationService notificationService;
    private final BookingFeignClient bookingFeignClient;

    public SalonNotificationController(NotificationService notificationService, BookingFeignClient bookingFeignClient) {
        this.notificationService = notificationService;
        this.bookingFeignClient = bookingFeignClient;
    }


    @GetMapping("/salon/{salonId}")
    public ResponseEntity<List<NotificationDto>> getNotificationBySalonId(@PathVariable Long salonId) throws Exception {
        List<Notification> notifications=notificationService.getNotificationBySalonId(salonId);
        List<NotificationDto> notificationDtos=notifications.stream().map(notification->{
            BookingDto bookingDto=null;
            try {
                bookingDto= bookingFeignClient.getBookingById(notification.getBookingId()).getBody();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            return NotificationMapper.toDto(notification,bookingDto);

        }).toList();
        return ResponseEntity.ok(notificationDtos);
    }
}
