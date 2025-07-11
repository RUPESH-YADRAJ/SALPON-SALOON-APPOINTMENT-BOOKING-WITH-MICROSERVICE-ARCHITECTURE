package org.nrr.notification.servcice.impl;
import lombok.RequiredArgsConstructor;
import org.nrr.notification.payload.dto.NotificationDto;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@Controller
@RequiredArgsConstructor
@Service
public class RealTimeCommunicationService {

    private final SimpMessagingTemplate template;



    public void sendNotification(NotificationDto notificationDto) {
        template.convertAndSend(
                "/notification/user/"+notificationDto.getUserId(),
                notificationDto
        );
        template.convertAndSend(
                "/notification/salon/"+notificationDto.getSalonId(),
                notificationDto
        );
    }
}
