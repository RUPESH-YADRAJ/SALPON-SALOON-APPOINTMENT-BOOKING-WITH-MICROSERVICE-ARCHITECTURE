package org.nrr.payment_service.messaging;

import lombok.RequiredArgsConstructor;
import org.nrr.payment_service.payload.dto.NotificationDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationEventProducer {

    private final RabbitTemplate rabbitTemplate;

    public void sentNotification(Long bookingId,Long userId,Long salonId){
        NotificationDto notificationDto=new NotificationDto();
        notificationDto.setBookingId(bookingId);
        notificationDto.setUserId(userId);
        notificationDto.setSalonId(salonId);
        notificationDto.setDescription( "New booking got confirmed");
        notificationDto.setType("BOOKING");
        rabbitTemplate.convertAndSend("notification-queue",notificationDto);

    }
}
