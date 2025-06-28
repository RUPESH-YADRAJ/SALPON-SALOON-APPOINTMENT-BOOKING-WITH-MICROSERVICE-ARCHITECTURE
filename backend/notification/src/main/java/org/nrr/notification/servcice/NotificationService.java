package org.nrr.notification.servcice;

import org.nrr.notification.model.Notification;
import org.nrr.notification.payload.dto.NotificationDto;

import java.util.List;

public interface NotificationService {
    NotificationDto createNotification(Notification notification) throws Exception;
    List<Notification> getNotificationByUserId(Long userId);
    List<Notification> getNotificationBySalonId(Long salonId);
    Notification markNotificationAsRead(Long notificationId) throws Exception;
}
