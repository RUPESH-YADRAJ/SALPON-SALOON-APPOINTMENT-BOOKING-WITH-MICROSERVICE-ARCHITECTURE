package org.nrr.booking_service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentLinkResponse {
    private String payment_link_url;
    private String getPayment_link_id;
}
