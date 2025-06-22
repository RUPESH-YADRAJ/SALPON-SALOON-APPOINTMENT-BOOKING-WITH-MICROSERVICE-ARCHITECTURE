package org.nrr.payment_service.controller;

import org.nrr.payment_service.domain.PaymentMethod;
import org.nrr.payment_service.model.PaymentOrder;
import org.nrr.payment_service.payload.dto.BookingDto;
import org.nrr.payment_service.payload.dto.UserDto;
import org.nrr.payment_service.payload.response.PaymentLinkResponse;
import org.nrr.payment_service.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/create")
    public ResponseEntity<PaymentLinkResponse> createPaymentLink(
            @RequestBody BookingDto bookingDto,
            @RequestParam PaymentMethod paymentMethod
            )
    {
        UserDto userDto= UserDto.builder()
                .fullName("Nishan Giri")
                .email("girinishan81@gmail.com")
                .id(1L)
                .phone("9809785272")
                .build();
        PaymentLinkResponse res=paymentService.createOrder(userDto, bookingDto, paymentMethod);
        return ResponseEntity.ok(res);
    }


    @GetMapping("{paymentOrderId}")
    public ResponseEntity<PaymentOrder> getPaymentOrderById(
            @PathVariable Long paymentOrderId
    ) throws Exception {
        PaymentOrder res=paymentService.getPaymentOrderById(paymentOrderId);
        return ResponseEntity.ok(res);
    }


}
