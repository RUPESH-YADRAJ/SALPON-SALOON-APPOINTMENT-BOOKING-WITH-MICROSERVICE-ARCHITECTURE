package org.nrr.payment_service.messaging;

import jdk.jfr.Event;
import lombok.RequiredArgsConstructor;
import org.nrr.payment_service.model.PaymentOrder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookingEventProducer {

    private final RabbitTemplate rabbitTemplate;

    public void sentBookingUpdateEvent(PaymentOrder paymentOrder) {
        rabbitTemplate.convertAndSend("booking-queue",paymentOrder);
    }
}
