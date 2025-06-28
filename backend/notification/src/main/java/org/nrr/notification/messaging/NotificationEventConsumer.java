package org.nrr.notification.messaging;

import lombok.RequiredArgsConstructor;
import org.nrr.notification.model.Notification;
import org.nrr.notification.servcice.NotificationService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationEventConsumer {

    private final NotificationService notificationService;

    @RabbitListener(queues = "notification-queue")
    public void sentNotificationEventConsumer(Notification notification) throws Exception {
        notificationService.createNotification(notification);
    }
}
