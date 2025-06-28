package org.nrr.notification.servcice.impl;


import org.nrr.notification.mapper.NotificationMapper;
import org.nrr.notification.model.Notification;
import org.nrr.notification.payload.dto.BookingDto;
import org.nrr.notification.payload.dto.NotificationDto;
import org.nrr.notification.repository.NotificationRepository;
import org.nrr.notification.servcice.NotificationService;
import org.nrr.notification.servcice.client.BookingFeignClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final BookingFeignClient bookingFeignClient;

    public NotificationImpl(NotificationRepository notificationRepository, BookingFeignClient bookingFeignClient) {
        this.notificationRepository = notificationRepository;
        this.bookingFeignClient = bookingFeignClient;
    }

    @Override
    public NotificationDto createNotification(Notification notification) throws Exception {
        Notification savedNotification=notificationRepository.save(notification);
        BookingDto bookingDto=bookingFeignClient.getBookingById(
                savedNotification.getBookingId()
        ).getBody();
        NotificationDto notificationDto= NotificationMapper.toDto(savedNotification,bookingDto);

        return notificationDto;
    }

    @Override
    public List<Notification> getNotificationByUserId(Long userId) {

        return notificationRepository.findByUserId(userId);
    }

    @Override
    public List<Notification> getNotificationBySalonId(Long salonId) {
        return notificationRepository.findBySalonId(salonId);
    }

    @Override
    public Notification markNotificationAsRead(Long notificationId) throws Exception {
        return notificationRepository.findById(notificationId).map(
                notification->{
                        notification.setIsRead(true);
                        return notificationRepository.save(notification);

                }
        ).orElseThrow(()->new Exception("notification not found"));
    }
}
