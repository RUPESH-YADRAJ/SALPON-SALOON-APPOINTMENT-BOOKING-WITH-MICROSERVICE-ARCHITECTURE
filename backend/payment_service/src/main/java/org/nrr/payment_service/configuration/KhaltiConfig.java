package org.nrr.payment_service.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "khalti")
@Data
public class KhaltiConfig {
    private String secretKey;
    private String initiateUrl;
    private String lookupUrl;

}