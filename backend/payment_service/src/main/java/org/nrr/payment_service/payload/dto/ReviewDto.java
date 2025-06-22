package org.nrr.payment_service.payload.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReviewDto {

    private Long id;

    private Long property;

    private Long reviewer;

    private String reviewText;

    private Integer rating;

    private LocalDateTime createdAt;
}
