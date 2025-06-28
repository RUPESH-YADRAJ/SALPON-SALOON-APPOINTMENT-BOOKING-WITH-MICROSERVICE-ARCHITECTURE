package org.nrr.booking_service.messaging;

import lombok.RequiredArgsConstructor;
import org.nrr.booking_service.model.Booking;
import org.nrr.booking_service.model.PaymentOrder;
import org.nrr.booking_service.service.BookingService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookingEventConsumer {

    private final BookingService bookingService;

    @RabbitListener(queues = "booking-queue")
    public void bookingUpdateListener(PaymentOrder paymentOrder) throws Exception {
        System.out.println("🔔 Received PaymentOrder from booking-queue: " + paymentOrder);
        bookingService.bookingSuccess(paymentOrder);
    }
}
