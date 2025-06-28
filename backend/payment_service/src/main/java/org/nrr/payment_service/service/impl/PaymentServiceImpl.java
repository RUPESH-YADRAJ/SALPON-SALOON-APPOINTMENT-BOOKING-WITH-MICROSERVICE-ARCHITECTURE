package org.nrr.payment_service.service.impl;

import org.nrr.payment_service.domain.PaymentMethod;
import org.nrr.payment_service.domain.PaymentOrderStatus;
import org.nrr.payment_service.messaging.BookingEventProducer;
import org.nrr.payment_service.messaging.NotificationEventProducer;
import org.nrr.payment_service.model.PaymentOrder;
import org.nrr.payment_service.payload.dto.BookingDto;
import org.nrr.payment_service.payload.dto.PaymentLink;
import org.nrr.payment_service.payload.dto.UserDto;
import org.nrr.payment_service.payload.response.PaymentLinkResponse;
import org.nrr.payment_service.repository.PaymentOrderRepository;
import org.nrr.payment_service.service.PaymentService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;


@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentOrderRepository paymentOrderRepository;
    private final BookingEventProducer bookingEventProducer;
    private final NotificationEventProducer notificationEventProducer;

    @Value("${khalti.secret-key}")
    private String KHALTI_SECRET_KEY;

    @Value("${stripe.api.key}")
    private String STRIPE_SECRET_KEY;



    public PaymentServiceImpl(PaymentOrderRepository paymentOrderRepository,  BookingEventProducer bookingEventProducer, NotificationEventProducer notificationEventProducer) {
        this.paymentOrderRepository = paymentOrderRepository;
        this.bookingEventProducer = bookingEventProducer;
        this.notificationEventProducer = notificationEventProducer;
    }

    @Override
    public PaymentLinkResponse createOrder(UserDto userDto,
                                           BookingDto bookingDTO,
                                           PaymentMethod paymentMethod) {

        Long amount=(long) bookingDTO.getTotalPrice();

        PaymentOrder order = PaymentOrder.builder()
                .amount(amount)
                .userId(userDto.getId())
                .paymentMethod(paymentMethod)
                .bookingId(bookingDTO.getId())
                .status(PaymentOrderStatus.PENDING)
                .salonId(bookingDTO.getSalonId())
                .build();
        System.out.println("------------"+userDto.getId()+"------------");
        PaymentOrder savedOrder=paymentOrderRepository.save(order);

        PaymentLinkResponse paymentLinkResponse =PaymentLinkResponse.builder().build();

        if(paymentMethod.equals(PaymentMethod.KHALTI)){
            PaymentLink payment=createKhaltiPaymentLink(userDto,savedOrder.getAmount(),savedOrder.getId());

            String paymentUrl=payment.getPayment_url();
            String paymentUrlId=payment.getPidx();
            paymentLinkResponse.setPayment_link_url(paymentUrl);
            paymentLinkResponse.setGetPayment_link_id(paymentUrlId);


            savedOrder.setPaymentLinkId(paymentUrlId);
            paymentOrderRepository.save(savedOrder);

        }


        return paymentLinkResponse;
    }

    @Override
    public PaymentOrder getPaymentOrderById(Long id) throws Exception {
        PaymentOrder paymentOrder=paymentOrderRepository.findById(id).orElse(null);
        if(paymentOrder==null){
            throw new Exception("payment order not found");
        }
        return paymentOrder;
    }

    @Override
    public PaymentOrder getPaymentOrderByPaymentId(String paymentId) {
            return paymentOrderRepository.findByPaymentLinkId(paymentId);
    }

    @Override
    public String createStripePaymentLink(UserDto userDto, Long amount, Long orderId) {
        return "";
    }

    @Override
    public PaymentLink createKhaltiPaymentLink(UserDto userDto, long amount, Long orderId) {

        String url = "https://a.khalti.com/api/v2/epayment/initiate/";

        RestTemplate restTemplate = new RestTemplate();

        // Prepare headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Key " + KHALTI_SECRET_KEY);

        // Prepare request body
        Map<String, Object> request = getStringObjectMap(userDto, amount, orderId);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(request, headers);

        ResponseEntity<PaymentLink> response = restTemplate.postForEntity(url, entity, PaymentLink.class);

        return response.getBody();
    }



    @Override
    public Boolean proceedPayment(PaymentOrder paymentOrder,
                                  String paymentId,
                                  String paymentLinkId) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://a.khalti.com/api/v2/epayment/lookup/"))
                    .header("Authorization","key "+ KHALTI_SECRET_KEY)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString("{\"pidx\":\"" + paymentLinkId + "\"}"))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JsonObject jsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();
            String status = jsonResponse.has("status") ? jsonResponse.get("status").getAsString() : "";

            switch (status.toLowerCase()) {
                case "completed":
                case "confirmed":
                    bookingEventProducer.sentBookingUpdateEvent(paymentOrder);
                    notificationEventProducer.sentNotification(paymentOrder.getBookingId(),
                            paymentOrder.getUserId(),
                            paymentOrder.getSalonId());
                    paymentOrder.setStatus(PaymentOrderStatus.SUCCESS);
                    paymentOrderRepository.save(paymentOrder);
                    return true;

                case "pending":
                    // Optional: Set as intermediate state
                    paymentOrder.setStatus(PaymentOrderStatus.PENDING);
                    paymentOrderRepository.save(paymentOrder);
                    return false;

                case "initiated":
                case "refunded":
                case "expired":
                case "cancelled":
                case "failed":
                default:
                    paymentOrder.setStatus(PaymentOrderStatus.FAILED);
                    paymentOrderRepository.save(paymentOrder);
                    return false;
            }

        } catch (Exception e) {
            paymentOrder.setStatus(PaymentOrderStatus.FAILED);
            return false;
        }
    }


    private static Map<String, Object> getStringObjectMap(UserDto userDto, long amount, Long orderId) {
        Map<String, Object> request = new HashMap<>();
        request.put("return_url", "https://localhost:3000/payment-success/"+orderId);
        request.put("website_url", "https://localhost:5006/");
        request.put("amount", amount * 100);
        request.put("purchase_order_id", orderId);
        request.put("currency", "NPR");
        request.put("purchase_order_name", "Service Payment");

        Map<String, String> customerInfo = new HashMap<>();
        customerInfo.put("name", userDto.getFullName());
        customerInfo.put("email", userDto.getEmail());
        customerInfo.put("phone", userDto.getPhoneNumber());

        request.put("customer_info", customerInfo);
        return request;
    }


}
