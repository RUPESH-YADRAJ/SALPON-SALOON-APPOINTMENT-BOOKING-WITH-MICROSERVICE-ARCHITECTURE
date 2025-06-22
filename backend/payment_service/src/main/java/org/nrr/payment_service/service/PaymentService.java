package org.nrr.payment_service.service;




import org.nrr.payment_service.domain.PaymentMethod;
import org.nrr.payment_service.model.PaymentOrder;
import org.nrr.payment_service.payload.dto.BookingDto;
import org.nrr.payment_service.payload.dto.PaymentLink;
import org.nrr.payment_service.payload.dto.UserDto;
import org.nrr.payment_service.payload.response.PaymentLinkResponse;

public interface PaymentService {
PaymentLinkResponse createOrder(UserDto userDto,
                                BookingDto bookingDTO,
                                PaymentMethod paymentMethod);

PaymentOrder getPaymentOrderById(Long id) throws Exception;
PaymentOrder getPaymentOrderByPaymentId(String paymentId);

String createStripePaymentLink(UserDto userDto,
                               Long amount,
                               Long orderId);
org.nrr.payment_service.payload.dto.PaymentLink createKhaltiPaymentLink(UserDto userDto, long amount, Long orderId);

Boolean proceedPayment(PaymentOrder paymentOrder,String paymentId,String paymentLinkId);


}
