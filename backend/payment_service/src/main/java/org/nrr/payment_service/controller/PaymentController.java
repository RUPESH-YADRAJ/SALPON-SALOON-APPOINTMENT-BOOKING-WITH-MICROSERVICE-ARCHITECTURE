package org.nrr.payment_service.controller;

import org.nrr.payment_service.domain.PaymentMethod;
import org.nrr.payment_service.model.PaymentOrder;
import org.nrr.payment_service.payload.dto.BookingDto;
import org.nrr.payment_service.payload.dto.UserDto;
import org.nrr.payment_service.payload.response.PaymentLinkResponse;
import org.nrr.payment_service.service.PaymentService;
import org.nrr.payment_service.service.client.UserFeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    private final PaymentService paymentService;
    private final UserFeignClient userFeignClient;


    public PaymentController(PaymentService paymentService, UserFeignClient userFeignClient) {
        this.paymentService = paymentService;
        this.userFeignClient = userFeignClient;
    }

    @PostMapping("/create")
    public ResponseEntity<PaymentLinkResponse> createPaymentLink(
            @RequestBody BookingDto bookingDto,
            @RequestParam PaymentMethod paymentMethod,
            @RequestHeader("Authorization") String jwt
            ) throws Exception {
        UserDto userDto1=userFeignClient.getUserFromJwtToken(jwt).getBody();
        if(userDto1==null) {
            throw new Exception("Fetching user detail failed");
        }
        UserDto userDto= UserDto.builder()
                .id(userDto1.getId())
                .email(userDto1.getEmail())
                .phoneNumber(String.valueOf(userDto1.getPhoneNumber()))
                .fullName(userDto1.getFullName())
                .build();
        System.out.println(userDto.getId());
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
