package org.nrr.notification.controller;

import org.nrr.notification.mapper.NotificationMapper;
import org.nrr.notification.model.Notification;
import org.nrr.notification.payload.dto.BookingDto;
import org.nrr.notification.payload.dto.NotificationDto;
import org.nrr.notification.repository.NotificationRepository;
import org.nrr.notification.servcice.NotificationService;
import org.nrr.notification.servcice.client.BookingFeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    private final NotificationService notificationService;
    private final BookingFeignClient bookingFeignClient;

    public NotificationController(NotificationService notificationService, BookingFeignClient bookingFeignClient) {
        this.notificationService = notificationService;
        this.bookingFeignClient = bookingFeignClient;
    }

    @PostMapping
    public ResponseEntity<NotificationDto> createNotification(@RequestBody Notification notification) throws Exception {
        return ResponseEntity.ok(notificationService.createNotification(notification));
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<List<NotificationDto>> getNotificationByUserId(@PathVariable Long userId) throws Exception {
        List<Notification> notifications=notificationService.getNotificationByUserId(userId);
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

    @PutMapping("/{notificationId}/read")
    public ResponseEntity<NotificationDto> markNotificationAsRead(@PathVariable Long notificationId) throws Exception {
        Notification notification=notificationService.markNotificationAsRead(notificationId);
        BookingDto bookingDto=bookingFeignClient.getBookingById(notification.getBookingId()).getBody();
        return ResponseEntity.ok(NotificationMapper.toDto(notification,bookingDto));
    }


}
