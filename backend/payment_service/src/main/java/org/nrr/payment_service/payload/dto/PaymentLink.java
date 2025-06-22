package org.nrr.payment_service.payload.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentLink {
    private String payment_url;
    private String pidx;
}
