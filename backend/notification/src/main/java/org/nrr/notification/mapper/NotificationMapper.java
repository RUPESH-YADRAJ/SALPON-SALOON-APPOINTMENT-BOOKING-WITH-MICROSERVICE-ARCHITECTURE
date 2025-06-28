package org.nrr.notification.mapper;

import org.nrr.notification.model.Notification;
import org.nrr.notification.payload.dto.BookingDto;
import org.nrr.notification.payload.dto.NotificationDto;

public class NotificationMapper {
    public static NotificationDto toDto(Notification notification, BookingDto bookingDto) {
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setId(notification.getId());
        notificationDto.setType(notification.getType());
        notificationDto.setIsRead(notification.getIsRead());
        notificationDto.setDescription(notification.getDescription());
        notificationDto.setBookingId(bookingDto.getId());
        notificationDto.setUserId(notification.getUserId());
        notificationDto.setSalonId(notification.getSalonId());
        notificationDto.setCreatedAt(notification.getCreatedAt());
        return notificationDto;


    }
}
